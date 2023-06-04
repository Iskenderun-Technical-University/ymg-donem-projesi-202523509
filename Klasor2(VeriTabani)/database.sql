-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 03 Haz 2022, 14:10:10
-- Sunucu sürümü: 8.0.29
-- PHP Sürümü: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `projemdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `faturalar`
--

CREATE TABLE `faturalar` (
  `fatura_no` int NOT NULL,
  `kulAd` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `Aciklama` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `Tutar` double NOT NULL,
  `Tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `faturalar`
--

INSERT INTO `faturalar` (`fatura_no`, `kulAd`, `Aciklama`, `Tutar`, `Tarih`) VALUES
(1, 'zeynep', 'nakit', 150, '2021-09-10'),
(2, 'admin', 'kredi kart', 200, '2022-05-04'),
(3, 'admin', 'nakit', 50.75, '2022-05-05'),
(4, 'shahzanan', 'kredi kart', 55, '2022-05-05'),
(5, 'shahzanan', 'nakit', 99.5, '2022-05-06');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `kul_id` int NOT NULL,
  `kulAd` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `sifre` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `yetkili` int NOT NULL,
  `telefon` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`kul_id`, `kulAd`, `sifre`, `yetkili`, `telefon`) VALUES
(1, 'admin', '123', 1, 543786425),
(2, 'shahzanan', '1234', 0, 532333333),
(3, 'zeynep', '4321', 1, 214783647);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `urun_id` int NOT NULL,
  `urun_ad` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL,
  `adet` int NOT NULL,
  `satis_fiyati` double NOT NULL,
  `toptan_fiyat` double NOT NULL,
  `yorum` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`urun_id`, `urun_ad`, `adet`, `satis_fiyati`, `toptan_fiyat`, `yorum`) VALUES
(1, 'HB kalem', 60, 8, 8, 'Adana'),
(2, 'A5 telli defter 96', 120, 9, 6, 'Antakya'),
(3, 'kitap', 12, 25.5, 18, 'İstanbul'),
(4, 'defter', 45, 30, 25, 'İskenderun');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `faturalar`
--
ALTER TABLE `faturalar`
  ADD PRIMARY KEY (`fatura_no`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`kul_id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`urun_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `faturalar`
--
ALTER TABLE `faturalar`
  MODIFY `fatura_no` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `kul_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `urun_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
