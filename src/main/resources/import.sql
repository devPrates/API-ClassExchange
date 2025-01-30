INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC') ON CONFLICT (role_id) DO NOTHING;

INSERT INTO tb_campus (name, sigla, endereco) VALUES ('Campus Naviraí', 'NV', 'Rua Principal, 123, Centro'), ('Campus Dourados', 'GD', 'Avenida Norte, 456, Bairro Norte'), ('Campus Campo Grande', 'CG', 'Avenida Sul, 789, Bairro Sul');

INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Análise e Desenvilvimento de Sistemas', 'TADS', 1, NOW(), NOW()), ('Agronomia', 'AGRO', 1, NOW(), NOW()), ('Agricultura', 'AGRI', 1, NOW(), NOW());
INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Administração', 'ADM', 2, NOW(), NOW()), ('Economia', 'ECO', 2, NOW(), NOW()), ('Contabilidade', 'CONT', 2, NOW(), NOW());
INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Direito', 'DIR', 3, NOW(), NOW()), ('Relações Internacionais', 'RI', 3, NOW(), NOW()), ('Ciências Políticas', 'CP', 3, NOW(), NOW());


