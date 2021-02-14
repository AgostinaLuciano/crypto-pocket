DROP TABLE IF EXISTS "users";

CREATE TABLE IF NOT EXISTS "users" (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT TRUE
);

DROP TABLE IF EXISTS "cryptocurrency";

CREATE TABLE IF NOT EXISTS "cryptocurrency" (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  symbol VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS "transaction";

CREATE TABLE IF NOT EXISTS "transaction" (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INTEGER NOT NULL,
  crypto_currency_id INTEGER NOT NULL,
  amount NUMERIC(22,0) NOT NULL,
  operation_type VARCHAR(50) NOT NULL,
  transaction_date VARCHAR(255) NOT NULL,
  CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES "users"(id),
  CONSTRAINT fk_cryptocurrency FOREIGN KEY(crypto_currency_id) REFERENCES "cryptocurrency"(id)
);

INSERT INTO "cryptocurrency" (name, symbol) VALUES
('Bitcoin',	'BTC'),
('Ethereum',	'ETH'),
('Tether',	'USDT'),
('XRP',	'XRP'),
('Polkadot',	'DOT'),
('Cardano',	'ADA'),
('Chainlink',	'LINK'),
('Litecoin',	'LTC'),
('Binance Coin',	'BNB'),
('Bitcoin Cash',	'BCH'),
('Stellar',	'XLM'),
('Dogecoin',	'DOGE'),
('USD Coin',	'USDC');