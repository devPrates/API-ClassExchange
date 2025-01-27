INSERT INTO tb_roles (role_id, name) VALUES (1, 'admin') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'basic') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (1, 'admin') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'basic') ON CONFLICT (role_id) DO NOTHING;
INSERT INTO tb_users (user_id, username, password) VALUES ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454', 'admin', '$2a$10$mysMwlUS1659V4G3wiRPIuMh/qjlJGKVk/6Vy0aXof48u/tpgk9sO') ON CONFLICT (user_id) DO NOTHING;
INSERT INTO tb_users_roles (user_id, role_id) VALUES ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454', 1) ON CONFLICT (user_id, role_id) DO NOTHING;
