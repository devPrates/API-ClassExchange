INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC') ON CONFLICT (role_id) DO NOTHING;

INSERT INTO tb_campus (name, sigla, endereco) VALUES ('Campus Naviraí', 'NV', 'Rua Principal, 123, Centro'), ('Campus Dourados', 'GD', 'Avenida Norte, 456, Bairro Norte'), ('Campus Campo Grande', 'CG', 'Avenida Sul, 789, Bairro Sul');