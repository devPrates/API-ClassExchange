INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC') ON CONFLICT (role_id) DO NOTHING;

INSERT INTO tb_campus (name, sigla, endereco) VALUES ('Campus Naviraí', 'NV', 'Rua Principal, 123, Centro'), ('Campus Dourados', 'GD', 'Avenida Norte, 456, Bairro Norte'), ('Campus Campo Grande', 'CG', 'Avenida Sul, 789, Bairro Sul');

INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Análise e Desenvilvimento de Sistemas', 'TADS', 1, NOW(), NOW()), ('Agronomia', 'AGRO', 1, NOW(), NOW()), ('Agricultura', 'AGRI', 1, NOW(), NOW());
INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Administração', 'ADM', 2, NOW(), NOW()), ('Economia', 'ECO', 2, NOW(), NOW()), ('Contabilidade', 'CONT', 2, NOW(), NOW());
INSERT INTO tb_cursos (name, sigla, campus_id, created_at, updated_at) VALUES ('Direito', 'DIR', 3, NOW(), NOW()), ('Relações Internacionais', 'RI', 3, NOW(), NOW()), ('Ciências Políticas', 'CP', 3, NOW(), NOW());

INSERT INTO tb_turmas (nome, ano, curso_id) VALUES ('Turma A', 2025, 1), ('Turma B', 2024, 1), ('Turma C', 2023, 1);
INSERT INTO tb_turmas (nome, ano, curso_id) VALUES ('Turma D', 2023, 2), ('Turma E', 2024, 2), ('Turma F', 2025, 2);

INSERT INTO tb_periodos (nome, tipo_periodo, numero, ano, inicio, fim, turma_id) VALUES ('1º Semestre 2023', 'SEMESTRAL', 1, 2023, '2023-01-01T00:00:00Z', '2023-06-30T23:59:59Z', 1), ('2º Semestre 2023', 'SEMESTRAL', 2, 2023, '2023-07-01T00:00:00Z', '2023-12-31T23:59:59Z', 1), ('1º Semestre 2024', 'SEMESTRAL', 1, 2024, '2024-01-01T00:00:00Z', '2024-06-30T23:59:59Z', 1), ('2º Semestre 2024', 'SEMESTRAL', 2, 2024, '2024-07-01T00:00:00Z', '2024-12-31T23:59:59Z', 1), ('1º Semestre 2025', 'SEMESTRAL', 1, 2025, '2025-01-01T00:00:00Z', '2025-06-30T23:59:59Z', 1), ('2º Semestre 2025', 'SEMESTRAL', 2, 2025, '2025-07-01T00:00:00Z', '2025-12-31T23:59:59Z', 1);

INSERT INTO tb_disciplinas (nome, carga_horaria, periodo_id, turma_id) VALUES ('Matemática Discreta', 60, 1, 1), ('Programação Orientada a Objetos', 80, 1, 1), ('Estruturas de Dados', 70, 1, 1), ('Banco de Dados', 75, 1, 1);