
CREATE TABLE IF NOT EXISTS public.groups
(
  id SERIAL PRIMARY KEY NOT NULL,
  title CHAR(100) NOT NULL
);
CREATE UNIQUE INDEX groups_title_uindex ON public.groups (title);
CREATE UNIQUE INDEX groups_id_uindex ON public.groups (id);
COMMENT ON TABLE public.groups IS 'таблица групп';

INSERT  INTO groups (title) VALUES
  ('a'),
  ('b'),
  ('c');


CREATE TABLE IF NOT EXISTS public.users
(
  id SERIAL PRIMARY KEY NOT NULL,
  login CHAR(100) NOT NULL,
  password CHAR(100) NOT NULL,
  enable BOOLEAN NOT NULL
);
CREATE UNIQUE INDEX users_login_uindex ON public.users (login);
COMMENT ON TABLE public.users IS 'таблица пользователей';

INSERT  INTO users (login, password) VALUES
  ('A','root'),
  ('B','root'),
  ('C','root');


CREATE TABLE IF NOT EXISTS public.contacts(
  id SERIAL  NOT NULL CONSTRAINT pk_contacts PRIMARY KEY,
  fio CHAR(255) NOT NULL ,
  phone CHAR(100),
  email CHAR(100),
  user_id INTEGER NOT NULL,
  CONSTRAINT fk_contact FOREIGN KEY (user_id)
  REFERENCES public.users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- COPY public.contacts(id,fio,phone,email,user_id)
-- FROM 'D:\sql\contacts.csv' WITH DELIMITER ',' CSV;

CREATE TABLE IF NOT EXISTS public.contact_group
(
  contact_id INT NOT NULL,
  group_id INT NOT NULL,
  CONSTRAINT contact_group_contact_id_fk FOREIGN KEY (contact_id) REFERENCES contacts (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT contact_group_group_id_fk FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE public.role
(
  id SERIAL PRIMARY KEY NOT NULL,
  title CHAR(50) NOT NULL,
  description CHARACTER
);
CREATE UNIQUE INDEX role_title_uindex ON public.role (title);


CREATE TABLE public.user_role
(
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  CONSTRAINT user_role_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX user_role_user_id_uindex ON public.user_role (user_id);
CREATE UNIQUE INDEX user_role_role_id_uindex ON public.user_role (role_id);
-- COPY public.contact_group(contact_id, group_id)
-- FROM 'D:\sql\contacts_groups.csv' WITH DELIMITER ',' CSV;