<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210303230645 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reclamation (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) NOT NULL, datereclamation DATE NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation_piece_r (reclamation_id INT NOT NULL, piece_r_id INT NOT NULL, INDEX IDX_3AA077C62D6BA2D9 (reclamation_id), INDEX IDX_3AA077C67D1A9070 (piece_r_id), PRIMARY KEY(reclamation_id, piece_r_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reclamation_piece_r ADD CONSTRAINT FK_3AA077C62D6BA2D9 FOREIGN KEY (reclamation_id) REFERENCES reclamation (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation_piece_r ADD CONSTRAINT FK_3AA077C67D1A9070 FOREIGN KEY (piece_r_id) REFERENCES piece_r (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reclamation_piece_r DROP FOREIGN KEY FK_3AA077C62D6BA2D9');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE reclamation_piece_r');
    }
}
