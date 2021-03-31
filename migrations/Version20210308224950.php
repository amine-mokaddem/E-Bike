<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210308224950 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE location ADD duree VARCHAR(255) NOT NULL, CHANGE idvelol_id idvelol_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE performance CHANGE id_util_id id_util_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation CHANGE piecer_id piecer_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE veloav CHANGE commande_id commande_id INT DEFAULT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE location DROP duree, CHANGE idvelol_id idvelol_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE performance CHANGE id_util_id id_util_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation CHANGE piecer_id piecer_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE veloav CHANGE commande_id commande_id INT DEFAULT NULL');
    }
}
