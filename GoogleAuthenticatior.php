<?php

namespace App\Security;

use App\Entity\Utilisateur;

// your user entity
use Doctrine\ORM\EntityManagerInterface;
use KnpU\OAuth2ClientBundle\Security\Authenticator\SocialAuthenticator;
use KnpU\OAuth2ClientBundle\Client\Provider\GoogleClient\Client;
use KnpU\OAuth2ClientBundle\Client\ClientRegistry;
use League\OAuth2\Client\Provider\GoogleUser;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\RouterInterface;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Exception\AuthenticationException;
use Symfony\Component\Security\Core\User\UserProviderInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;

class GoogleAuthenticatior extends SocialAuthenticator
{
    private $clientRegistry;
    private $em;
    private $router;

    public function __construct(ClientRegistry $clientRegistry, EntityManagerInterface $em, RouterInterface $router)
    {
        $this->clientRegistry = $clientRegistry;
        $this->em = $em;
        $this->router = $router;
    }

    public function supports(Request $request)
    {
        // continue ONLY if the current ROUTE matches the check ROUTE
        return $request->attributes->get('_route') === 'connect_google_check';
    }

    public function getCredentials(Request $request)
    {
        // this method is only called if supports() returns true

        // For Symfony lower than 3.4 the supports method need to be called manually here:
        // if (!$this->supports($request)) {
        //     return null;
        // }

        return $this->fetchAccessToken($this->getGoogleClient());
    }

    public function getUser($credentials, UserProviderInterface $userProvider)
    {
        /** @var GoogleUser $GoogleUser */
        $GoogleUser = $this->getGoogleClient()
            ->fetchUserFromToken($credentials);

        $email = $GoogleUser->getEmail();


        $user = $this->em->getRepository(Utilisateur::class)
              ->findOneBy(['email' => $email]);
        if($user){
            return $user;
        }


        if (!$user) {
            $user = new Utilisateur();
            $user->setEmail($GoogleUser->getEmail());
            $user->setNom($GoogleUser->getName());
            $user->setPassword("0000");
            $user->setNumtel(77);
            $user->setType("client");
            $this->em->persist($user);
            $this->em->flush();

    return $user;
        }
    }

        /**
         * @return GoogleClient
         */
        public function getGoogleClient()
        {
            return $this->clientRegistry
                // "google" is the key used in config/packages/knpu_oauth2_client.yaml
                ->getClient('google');
        }

        public function onAuthenticationSuccess(Request $request, TokenInterface $token, $providerKey)
        {
            // change "app_homepage" to some route in your app
            $targetUrl = $this->router->generate('home');

            return new RedirectResponse($targetUrl);

            // or, on success, let the request continue to be handled by the controller
            //return null;
        }

        public function onAuthenticationFailure(Request $request, AuthenticationException $exception)
        {
            $message = strtr($exception->getMessageKey(), $exception->getMessageData());

            return new Response($message, Response::HTTP_FORBIDDEN);
        }

        /**
         * Called when authentication is needed, but it's not sent.
         * This redirects to the 'login'.
         */
        public function start(Request $request, AuthenticationException $authException = null)
        {
            return new RedirectResponse(
                '/connect/', // might be the site, where users choose their oauth provider
                Response::HTTP_TEMPORARY_REDIRECT
            );
        }

        // ...
    }

