-- Criação da extensão pgcrypto
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Criação da tabela user
CREATE TABLE "users" (
                        id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                        name VARCHAR(120) NOT NULL,
                        email VARCHAR(130) NOT NULL,
                        password VARCHAR(100) NOT NULL
);