-- noinspection SqlResolveForFile

INSERT INTO `role_table` (`id`,`role`) VALUES (1, 'ADMIN');
INSERT INTO `role_table` (`id`,`role`) VALUES (2, 'CLIENT');

INSERT INTO `user_table` (`id`,`convalidated`,`email`,`enabled`,`password`,`token`,`username`) VALUES (1, FALSE, 'test@gmail.com', TRUE, '$2a$10$gj94PO1PzXAnJONCjB6iD.a/39muFVJU58x5C8uvn7GQry1DSNfI6', null, 'testuser');
INSERT INTO `user_role`(`user_id`,`role_id`) VALUES ( 1, 2 );

INSERT INTO `emission` (`id`, `emission_name`, `unit_value`, `emission_date`, `default_coupon`, `max_purchase_per_day`, `max_uncontrolled_purchase`, `min_term`) VALUES ( 1,'Test Emission 1', 100, '2019-05-30T10:34:09', 5, 50, 1000, 60 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 1, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 2, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 3, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 4, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 5, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 6, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 7, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 8, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 9, 1 );
INSERT INTO `bond` (`id`, `emission_id`) VALUES ( 10, 1 );

