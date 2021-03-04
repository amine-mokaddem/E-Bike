<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210304022623 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE coach (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE coach_cours (coach_id INT NOT NULL, cours_id INT NOT NULL, INDEX IDX_E948EFB63C105691 (coach_id), INDEX IDX_E948EFB67ECF78B0 (cours_id), PRIMARY KEY(coach_id, cours_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE performance (id INT AUTO_INCREMENT NOT NULL, id_util_id INT DEFAULT NULL, depart VARCHAR(255) NOT NULL, arivee VARCHAR(255) NOT NULL, distance INT NOT NULL, temps VARCHAR(255) NOT NULL, INDEX IDX_82D7968111C087F0 (id_util_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE coach_cours ADD CONSTRAINT FK_E948EFB63C105691 FOREIGN KEY (coach_id) REFERENCES coach (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE coach_cours ADD CONSTRAINT FK_E948EFB67ECF78B0 FOREIGN KEY (cours_id) REFERENCES cours (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE performance ADD CONSTRAINT FK_82D7968111C087F0 FOREIGN KEY (id_util_id) REFERENCES utilisateur (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE coach_cours DROP FOREIGN KEY FK_E948EFB63C105691');
        $this->addSql('DROP TABLE coach');
        $this->addSql('DROP TABLE coach_cours');
        $this->addSql('DROP TABLE performance');
    }
}
