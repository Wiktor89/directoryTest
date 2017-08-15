DROP FUNCTION public.add_contact(CHAR, CHAR, CHAR, INTEGER);
DROP FUNCTION public.add_group(CHAR);
DROP FUNCTION public.app_contact_group(INTEGER, INTEGER);
DROP FUNCTION public.average_number_contacts_groups();
DROP FUNCTION public.average_number_contacts_user();
DROP FUNCTION public.delete_contact(INTEGER);
DROP FUNCTION public.delete_group(CHAR);
DROP FUNCTION public.delete_group_contact(INTEGER, INTEGER);
DROP FUNCTION public.get_contact(CHAR);
DROP FUNCTION public.get_contacts();
DROP FUNCTION public.get_contacts_group(CHAR);
DROP FUNCTION public.get_group(CHAR);
DROP FUNCTION public.get_groups();
DROP FUNCTION public.get_groups_contact(INTEGER);
DROP FUNCTION public.get_user(CHAR, CHAR);
DROP FUNCTION public.number_contacts(CHAR);
DROP FUNCTION public.number_users();
DROP FUNCTION public.quantity_groups_user(CHAR);
DROP FUNCTION public.update_contact(INTEGER, CHAR, CHAR, CHAR, INTEGER);
DROP FUNCTION public.update_group(CHAR, CHAR);
DROP FUNCTION public.user_with_contacts_min_10();
ALTER TABLE public.contact_group DROP CONSTRAINT contact_group_contact_id_fk;
ALTER TABLE public.contact_group DROP CONSTRAINT contact_group_group_id_fk;
ALTER TABLE public.contacts DROP CONSTRAINT fk_contact;
DROP TABLE  public.contact_group CASCADE;
DROP TABLE public.contacts CASCADE;
DROP TABLE public.users CASCADE;
DROP TABLE public.groups CASCADE;