CREATE TABLE IF NOT EXISTS setor(
	id INT AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS colaborador(
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	nome VARCHAR(40) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	email VARCHAR(40) NOT NULL UNIQUE,
	data_nascimento DATE NOT NULL,
	setor_id INT NOT NULL,
	
	CONSTRAINT fk_setor FOREIGN KEY (setor_id) REFERENCES setor (id)
);