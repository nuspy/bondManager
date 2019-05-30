
INSERT INTO `role_table` (`id`,`role`) VALUES (1, 'ADMIN');
INSERT INTO `role_table` (`id`,`role`) VALUES (2, 'CLIENT');

INSERT INTO `user_table` (`id`,`convalidated`,`email`,`enabled`,`password`,`token`,`username`) VALUES (1, FALSE, 'test@gmail.com', TRUE, '$2a$10$gj94PO1PzXAnJONCjB6iD.a/39muFVJU58x5C8uvn7GQry1DSNfI6', null, 'testuser');
INSERT INTO `user_role`(`user_id`,`role_id`) VALUES ( 1, 2 )