<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210306233343 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE veloav_commande (veloav_id INT NOT NULL, commande_id INT NOT NULL, INDEX IDX_F9AA37B53B0926AF (veloav_id), INDEX IDX_F9AA37B582EA2E54 (commande_id), PRIMARY KEY(veloav_id, commande_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE veloav_commande ADD CONSTRAINT FK_F9AA37B53B0926AF FOREIGN KEY (veloav_id) REFERENCES veloav (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE veloav_commande ADD CONSTRAINT FK_F9AA37B582EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE veloav_commande');
    }
}
