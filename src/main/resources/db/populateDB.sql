DELETE FROM user_roles;
delete from meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('User 1', 'user1@yandex.ru', 'password'),
  ('User 2', 'user2@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id)
VALUES
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002);

insert into meals (datetime, description, calories, userid)
values
  ('2016-09-24 10:20:00', 'Каша', 400, 100001),
  ('2016-09-24 15:00:00', 'Капуста с мясом', 650, 100001),
  ('2016-09-24 17:30:00', 'Кофе с конфетами', 350, 100001),
  ('2016-09-24 09:40:00', 'Яичница с беконом', 550, 100002),
  ('2016-09-24 14:00:00', 'Мясной пирог', 700, 100002),
  ('2016-09-24 17:30:00', 'Чай с сендвичем', 350, 100002);