-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 10 mars 2018 à 01:47
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `stock`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `nom_client` varchar(200) DEFAULT NULL,
  `tel_client` varchar(200) DEFAULT NULL,
  `adresse` varchar(200) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nbr_commande` int(11) DEFAULT '0',
  `prix_unitaire` int(11) DEFAULT '0',
  `date_payement` varchar(50) DEFAULT '0',
  `id_menu` int(11) DEFAULT '0',
  `montant` int(11) DEFAULT '0',
  `des_table` varchar(50) DEFAULT '0',
  `des_menu` varchar(50) DEFAULT '0',
  `addition` int(11) DEFAULT '0',
  `etat` varchar(50) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `nbr_commande`, `prix_unitaire`, `date_payement`, `id_menu`, `montant`, `des_table`, `des_menu`, `addition`, `etat`) VALUES
(0, 5, 3000, '2018-02-17 15:47:07.0', 1, 15000, 'table 01', 'Coca', 1, 'paye');

-- --------------------------------------------------------

--
-- Structure de la table `entre`
--

CREATE TABLE `entre` (
  `designation_produit` varchar(50) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `prixTotal` float DEFAULT NULL,
  `qte_recu` float DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL,
  `unite` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `numero` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL DEFAULT '0',
  `fournisseur` varchar(200) NOT NULL DEFAULT '0',
  `dateAcqui` varchar(200) NOT NULL DEFAULT '0',
  `datePayemn` varchar(200) NOT NULL DEFAULT '0',
  `etat` varchar(200) NOT NULL DEFAULT '0',
  `etat2` varchar(200) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL DEFAULT '0',
  `tel` varchar(200) NOT NULL DEFAULT '0',
  `mail` varchar(200) NOT NULL DEFAULT '0',
  `adress` varchar(200) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) DEFAULT NULL,
  `categorie` varchar(200) DEFAULT NULL,
  `pu` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id`, `designation`, `categorie`, `pu`) VALUES
(1, 'Coca', 'Boissons chaudes', '3000');

-- --------------------------------------------------------

--
-- Structure de la table `prelevement`
--

CREATE TABLE `prelevement` (
  `montant` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `prelevement`
--

INSERT INTO `prelevement` (`montant`, `date`) VALUES
('56', '2018-02-17 15:59:13.0'),
('4944', '2018-02-17 15:59:53.0');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `photo` varchar(50) NOT NULL DEFAULT '0',
  `designation` varchar(200) NOT NULL DEFAULT '0',
  `qte_actuel` float NOT NULL DEFAULT '0',
  `qte_alert` float NOT NULL DEFAULT '0',
  `unite` varchar(200) NOT NULL DEFAULT '0',
  `prixunit` varchar(200) NOT NULL DEFAULT '0',
  `categori` varchar(200) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `photo`, `designation`, `qte_actuel`, `qte_alert`, `unite`, `prixunit`, `categori`) VALUES
(1, '0', 'Sucre', 500, 50, 'Kg', '1500.0', 'Condiments');

-- --------------------------------------------------------

--
-- Structure de la table `produit_fourni`
--

CREATE TABLE `produit_fourni` (
  `designation` varchar(50) DEFAULT NULL,
  `fournisseur` varchar(50) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE `sortie` (
  `numero` int(11) NOT NULL,
  `designation` varchar(200) DEFAULT NULL,
  `qte_ex` float DEFAULT NULL,
  `unite` varchar(50) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `date` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `table1`
--

CREATE TABLE `table1` (
  `id_table` int(11) DEFAULT NULL,
  `designation_table` varchar(50) DEFAULT NULL,
  `nbr_place` int(11) DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `table1`
--

INSERT INTO `table1` (`id_table`, `designation_table`, `nbr_place`, `etat`) VALUES
(1, 'table 01', 4, 'Active');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`numero`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD PRIMARY KEY (`numero`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
