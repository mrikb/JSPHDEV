CREATE TABLE IF NOT EXISTS `model_opset` (
  `model` varchar(45) NOT NULL,
  `opset_id` int(11) NOT NULL,
  PRIMARY KEY (`model`,`opset_id`),
  KEY `fk_model_opset_2_idx` (`opset_id`),
  CONSTRAINT `fk_model_opset_2` FOREIGN KEY (`opset_id`) REFERENCES `opset` (`opset_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_model_opset_1` FOREIGN KEY (`model`) REFERENCES `model` (`model`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
