--  Хранимые процедуры  --

--  Функция вычисляет количество пользователей***
CREATE FUNCTION number_users () RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
i INTEGER;
BEGIN
SELECT count(*) FROM users into i;
RETURN i;
END;
$$;
--  Функция вычисляет количество пользователей

--  Функция вычисляет количество контактов каждого пользователя
CREATE FUNCTION number_contacts (name_user CHAR (100)) RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
i INTEGER;
BEGIN
SELECT COUNT(contacts.user_id) FROM contacts JOIN users ON contacts.user_id = users.id GROUP BY contacts.user_id, users.login HAVING users.login=name_user INTO i;
RETURN i;
END;
$$;
--  Функция вычисляет количество контактов каждого пользователя

--  Функция вычисляет количество групп каждого пользователя
CREATE FUNCTION quantity_groups_user (name_user CHAR (100)) RETURNS integer
LANGUAGE plpgsql
AS $$
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
$$;
--  Функция вычисляет количество групп каждого пользователя

--  Функция вычисляет среднее количество контактов в группах***
CREATE FUNCTION average_number_contacts_groups () RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
i INTEGER;
BEGIN
SELECT AVG(g) FROM (SELECT COUNT(*) AS g FROM servlet.contact_group GROUP BY group_id) AS x INTO i;
RETURN i;
END;
$$;
--  Функция вычисляет среднее количество контактов в группах

--  Функция вычисляет среднее количество контактов у пользователя***
CREATE FUNCTION average_number_contacts_user () RETURNS integer
  LANGUAGE plpgsql
  AS $$
DECLARE
i INTEGER;
BEGIN
SELECT AVG(cont) FROM (SELECT COUNT(*) AS cont FROM servlet.contacts GROUP BY user_id) AS x INTO i;
RETURN i;
END;
$$;
--  Функция вычисляет среднее количество контактов у пользователя

--  Функция вычисляет пользователя с количеством контактов < 10***
CREATE FUNCTION user_with_contacts_min_10 ()
RETURNS TABLE(user_name CHAR(100))
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT users.login FROM contacts JOIN users ON contacts.user_id = users.id
GROUP BY contacts.user_id,users.login HAVING COUNT(contacts.user_id) < 10;
END;
$$;
--  Функция вычисляет пользователя с количеством контактов < 10






--  Функция добавляет новый контакт***
CREATE FUNCTION add_contact (fio CHAR(255), phone CHAR(100),email CHAR(100), _id_user INTEGER )
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO contacts (fio, phone, email, user_id) VALUES (fio,phone,email,_id_user);
END;
$$;
--  Функция добавляет новый контакт

--  Функция редактирует контакт***
CREATE FUNCTION update_contact (_id INTEGER, _fio CHAR(255), _phone CHAR(100), _email CHAR(100), _user_id INTEGER)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE contacts SET fio = _fio, phone = _phone, email = _email, user_id = _user_id WHERE id = _id;
END;
$$;
--  Функция редактирует контакт

--  Функция удаляет контакт***
CREATE FUNCTION delete_contact (_id INTEGER)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM contacts WHERE id = _id;
END;
$$;
--  Функция удаляет контакт

--  Функция добавляет контакт в группу***
CREATE FUNCTION app_contact_group (_id_contact INTEGER, _id_group INTEGER)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO contact_group (contact_id, group_id) VALUES (_id_contact,_id_group);
END;
$$;
--  Функция добавляет контакт в группу

--  Функция удаляет группу у контакта***
CREATE FUNCTION delete_group_contact (_id_contact INTEGER, _id_group INTEGER)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM contact_group WHERE contact_id = _id_contact AND group_id = _id_group;
END;
$$;
--  Функция удаляет группу у контакта

--  Функция возвращает контакт***
CREATE FUNCTION get_contact (name CHARACTER)
RETURNS SETOF contacts
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT * FROM contacts WHERE fio = name;
END;
$$;
--  Функция возвращает контакт

--  Функция возвращает список контактов***
create function get_contacts() returns SETOF contacts
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT contacts.* FROM contacts;
END;
$$;
--  Функция возвращает список контактов

--  Функция возвращает список контактов определенной группы***
CREATE FUNCTION get_contacts_group (name CHAR(100)) RETURNS SETOF contacts
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT contacts.* FROM contacts JOIN contact_group ON contacts.id = contact_group.contact_id
JOIN  groups ON contact_group.group_id = groups.id WHERE title = name;
END;
$$;
--  Функция возвращает список контактов определенной группы

--  Функция возвращает список групп***
CREATE FUNCTION get_groups ()
RETURNS SETOF groups
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT * FROM groups;
END;
$$;
--  Функция возвращает список групп

--  Функция добавляет новую группу***
CREATE FUNCTION add_group (name CHAR(100))
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO groups(title) VALUES (name);
END;
$$;
--  Функция добавляет новую группу

--  Функция удаляет группу***
CREATE FUNCTION delete_group (name CHAR(255))
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
DELETE  FROM groups WHERE title = name;
END;
$$ ;
--  Функция удаляет группу

--  Функция обновляет группу***
CREATE FUNCTION update_group (newName CHARACTER, oldName CHARACTER)
RETURNS VOId
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE groups SET title = newName WHERE title = oldName;
END;
$$;
--  Функция обновляет группу


--  Функция возвращает пользователя***
CREATE FUNCTION get_user (_login CHARACTER, _password CHARACTER)
RETURNS SETOF users
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT users.* FROM users WHERE login = _login AND password = _password;
END;
$$;
--  Функция возвращает пользователя

--  Функция возвращает группу***
CREATE FUNCTION get_group_name (name CHARACTER)
RETURNS SETOF groups
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT groups.* FROM groups WHERE title = name;
END;
$$;
--  Функция возвращает группу

--  Функция возвращает группы контакта***
CREATE FUNCTION get_groups_contact (_id INTEGER)
RETURNS SETOF groups
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT groups.* FROM contacts JOIN  contact_group ON contacts.id = contact_group.contact_id
JOIN groups ON contact_group.group_id = groups.id WHERE contacts.id = _id;
END;
$$;
--  Функция возвращает группы контакта

--  Функция обновляет users***
CREATE FUNCTION update_users (_id INTEGER, _enable BOOLEAN)
RETURNS VOID
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE users SET enable = _enable WHERE id = _id;
END;
$$;
--  Функция обновляет users

--  Функция возвращает контакт***
CREATE FUNCTION get_contact (_id INTEGER)
RETURNS SETOF contacts
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY SELECT * FROM contacts WHERE id = _id;
END;
$$;
--  Функция возвращает контакт


