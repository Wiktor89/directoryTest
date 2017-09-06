
CREATE TABLE IF NOT EXISTS public.groups
(
  id SERIAL PRIMARY KEY NOT NULL,
  title CHAR(100) NOT NULL
);
CREATE UNIQUE INDEX groups_title_uindex ON public.groups (title);
CREATE UNIQUE INDEX groups_id_uindex ON public.groups (id);
COMMENT ON TABLE public.groups IS 'таблица групп';

INSERT  INTO public.groups (title) VALUES
  ('a'),
  ('b'),
  ('c');


CREATE TABLE IF NOT EXISTS public.users
(
  id SERIAL PRIMARY KEY NOT NULL,
  login CHARACTER(50) NOT NULL,
  password CHARACTER(50) NOT NULL,
  enable BOOLEAN NOT NULL DEFAULT FALSE,
  role_id INTEGER
);
CREATE UNIQUE INDEX users_login_uindex ON public.users (login);
COMMENT ON TABLE public.users IS 'таблица пользователей';

INSERT  INTO public.users (login, password) VALUES
  ('A','root'),
  ('B','root'),
  ('C','root');


CREATE TABLE IF NOT EXISTS public.contacts(
  id SERIAL  NOT NULL CONSTRAINT pk_contacts PRIMARY KEY,
  fio CHAR(255) NOT NULL ,
  phone CHAR(100) DEFAULT 'нет информации',
  email CHAR(100) DEFAULT 'нет информации',
  user_id INTEGER NOT NULL,
  CONSTRAINT fk_contact FOREIGN KEY (user_id)
  REFERENCES public.users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- COPY public.contacts(id,fio,phone,email,user_id)
-- FROM 'D:\sql.sql\contacts.csv' WITH DELIMITER ',' CSV;

CREATE TABLE IF NOT EXISTS public.contact_group
(
  contact_id INT NOT NULL,
  group_id INT NOT NULL,
  CONSTRAINT contact_group_contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contacts (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT contact_group_group_id_fk FOREIGN KEY (group_id) REFERENCES public.groups (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE public.role
(
  id SERIAL PRIMARY KEY NOT NULL,
  title CHAR(50) NOT NULL,
  description CHARACTER(255)
);
CREATE UNIQUE INDEX role_title_uindex ON public.role (title);

INSERT  INTO public.role (title, description) VALUES
  ('anonymous','ананимный'),
  ('user','зарегистрированный пользователь'),
  ('admin','администратор');

-- COPY public.contact_group(contact_id, group_id)
-- FROM 'D:\sql.sql\contacts_groups.csv' WITH DELIMITER ',' CSV;