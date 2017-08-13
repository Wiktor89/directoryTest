--  Хранимые процедуры  --

--  Функция вычисляет количество пользователей***
CREATE FUNCTION number_users () RETURNS integer AS '
DECLARE
i INTEGER;
BEGIN
SELECT count(*) FROM users into i;
RETURN i;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет количество пользователей

--  Функция вычисляет количество контактов каждого пользователя
CREATE FUNCTION number_contacts (name_user CHAR (100)) RETURNS integer AS '
DECLARE
i INTEGER;
BEGIN
SELECT COUNT(contacts.user_id) FROM contacts JOIN users ON contacts.user_id = users.id GROUP BY contacts.user_id, users.login HAVING users.login=name_user INTO i;
RETURN i;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет количество контактов каждого пользователя

--  Функция вычисляет количество групп каждого пользователя
CREATE FUNCTION quantity_groups_user (name_user CHAR (100)) RETURNS integer AS '
DECLARE
i INTEGER;
BEGIN
SELECT COUNT(DISTINCT groups.title),users.login
  FROM users JOIN contacts ON users.id = contacts.user_id
  JOIN contact_group ON contacts.id = contact_group.contact_id
  JOIN groups ON contact_group.group_id = groups.id
  GROUP BY users.login HAVING users.login = name_user INTO i;
RETURN i;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет количество групп каждого пользователя

--  Функция вычисляет среднее количество контактов в группах***
CREATE FUNCTION average_number_contacts_groups () RETURNS integer AS '
DECLARE
i INTEGER;
BEGIN
SELECT AVG(g) FROM (SELECT COUNT(*) AS g FROM public.contact_group GROUP BY group_id) AS x INTO i;
RETURN i;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет среднее количество контактов в группах

--  Функция вычисляет среднее количество контактов у пользователя***
CREATE FUNCTION average_number_contacts_user () RETURNS integer AS '
DECLARE
i INTEGER;
BEGIN
SELECT AVG(cont) FROM (SELECT COUNT(*) AS cont FROM public.contacts GROUP BY user_id) AS x INTO i;
RETURN i;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет среднее количество контактов у пользователя

--  Функция вычисляет пользователя с количеством контактов < 10***
CREATE FUNCTION user_with_contacts_min_10 ()
RETURNS TABLE(user_name CHAR(100)) AS '
BEGIN
RETURN QUERY SELECT users.login FROM contacts JOIN users ON contacts.user_id = users.id
GROUP BY contacts.user_id,users.login HAVING COUNT(contacts.user_id) < 10;
END;
' LANGUAGE plpgsql;
--  Функция вычисляет пользователя с количеством контактов < 10






--  Функция добавляет новый контакт***
CREATE FUNCTION add_contact (fio CHAR(255), phone CHAR(100),email CHAR(100), _id_user INTEGER )
RETURNS VOID AS '
BEGIN
INSERT INTO contacts (fio, phone, email, user_id) VALUES (fio,phone,email,_id_user);
END;
' LANGUAGE plpgsql;
--  Функция добавляет новый контакт

--  Функция редактирует контакт***
CREATE FUNCTION update_contact (_id INTEGER, _fio CHAR(255), _phone CHAR(100), _email CHAR(100), _user_id INTEGER)
RETURNS VOID AS '
BEGIN
UPDATE contacts SET fio = _fio, phone = _phone, email = _email, user_id = _user_id WHERE id = _id;
END;
' LANGUAGE plpgsql;
--  Функция редактирует контакт

--  Функция удаляет контакт***
CREATE FUNCTION delete_contact (_id INTEGER)
RETURNS VOID AS '
BEGIN
DELETE FROM contacts WHERE id = _id;
END;
' LANGUAGE plpgsql;
--  Функция удаляет контакт

--  Функция добавляет контакт в группу***
CREATE FUNCTION app_contact_group (_id_contact INTEGER, _id_group INTEGER)
RETURNS VOID AS '
BEGIN
INSERT INTO contact_group (contact_id, group_id) VALUES (_id_contact,_id_group);
END;
' LANGUAGE plpgsql;
--  Функция добавляет контакт в группу

--  Функция удаляет группу у контакта***
CREATE FUNCTION delete_group_contact (_id_contact INTEGER, _id_group INTEGER)
RETURNS VOID AS '
BEGIN
DELETE FROM contact_group WHERE contact_id = _id_contact AND group_id = _id_group;
END;
' LANGUAGE plpgsql;
--  Функция удаляет группу у контакта

--  Функция возвращает контакт***
CREATE FUNCTION get_contact (name CHARACTER)
RETURNS SETOF contacts AS '
BEGIN
RETURN QUERY SELECT * FROM contacts WHERE fio = name;
END;
' LANGUAGE plpgsql;
--  Функция возвращает контакт

--  Функция возвращает список контактов***
CREATE FUNCTION get_contacts () RETURNS SETOF contacts AS '
BEGIN
RETURN QUERY SELECT * FROM contacts;
END;
' LANGUAGE plpgsql;
--  Функция возвращает список контактов

--  Функция возвращает список контактов определенной группы***
CREATE FUNCTION get_contacts_group (name CHAR(100)) RETURNS SETOF contacts AS '
BEGIN
RETURN QUERY SELECT contacts.* FROM contacts JOIN contact_group ON contacts.id = contact_group.contact_id
JOIN  groups ON contact_group.group_id = groups.id WHERE title = name;
END;
' LANGUAGE plpgsql;
--  Функция возвращает список контактов определенной группы

--  Функция возвращает список групп***
CREATE FUNCTION get_groups ()
RETURNS SETOF groups AS '
BEGIN
RETURN QUERY SELECT * FROM groups;
END;
' LANGUAGE plpgsql;
--  Функция возвращает список групп

--  Функция добавляет новую группу***
CREATE FUNCTION add_group (name CHAR(100))
RETURNS VOID AS '
BEGIN
INSERT INTO groups(title) VALUES (name);
END;
' LANGUAGE plpgsql;
--  Функция добавляет новую группу

--  Функция удаляет группу***
CREATE FUNCTION delete_group (name CHAR(255))
RETURNS VOID AS '
BEGIN
DELETE  FROM groups WHERE title = name;
END;
' LANGUAGE plpgsql;
--  Функция удаляет группу

--  Функция обновляет группу***
CREATE FUNCTION update_group (newName CHARACTER, oldName CHARACTER)
RETURNS VOId AS '
BEGIN
UPDATE groups SET title = newName WHERE title = oldName;
END;
' LANGUAGE plpgsql;
--  Функция обновляет группу

--  Функция возвращает группы у контакта***
CREATE FUNCTION get_groups_contact (name CHARACTER)
RETURNS SETOF groups AS '
BEGIN
RETURN QUERY SELECT groups.* FROM contacts JOIN contact_group ON contacts.id = contact_group.contact_id
JOIN groups ON contact_group.group_id = groups.id WHERE contacts.fio = name GROUP BY title,groups.id;
END;
' LANGUAGE plpgsql;
--  Функция возвращает группы у контакта

--  Функция возвращает пользователя***
CREATE FUNCTION get_user (_login CHARACTER, _password CHARACTER)
RETURNS SETOF users AS '
BEGIN
RETURN QUERY SELECT users.* FROM users WHERE login = _login AND password = _password;
END;
' LANGUAGE plpgsql;
--  Функция возвращает пользователя

--  Функция возвращает группу***
CREATE FUNCTION get_group (name CHARACTER)
RETURNS SETOF groups AS '
BEGIN
RETURN QUERY SELECT groups.* FROM groups WHERE title = name;
END;
' LANGUAGE plpgsql;
--  Функция возвращает группу

