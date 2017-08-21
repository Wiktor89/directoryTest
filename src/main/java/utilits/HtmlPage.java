package utilits;

import models.Contact;
import models.Group;

import java.util.Set;

/**
 *
 */
public class HtmlPage {
	
	public static StringBuilder getHtmlPageRem(Set<Contact> contacts) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>List Contacts</title>\n<style>" +
				"table{\n width: 100%;\nbackground: white;\ncolor: white;\nborder-spacing: 1px;\n}" +
				"td, th{\nbackground: maroon;\npadding: 5px;\n}\n</style>" +
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<p><a href=/addContact>Back</a></p>");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>\n<th>id</th>\n<th>FIO</th>\n");
		stringBuilder.append("<th>Phone</th>\n<th>Email</th>\n<th>Group</th>\n<th>Action</th>");
		for (Contact contact : contacts) {
			stringBuilder.append("<tr>\n<th>");
			stringBuilder.append(contact.getId());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getFio());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getPhone());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getEmail());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getGroups());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append("<a href='? method=remove&id=" + String.valueOf(contact.getId()) + "'>remove</a>");
		}
		stringBuilder.append("</table>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
	
	public static StringBuilder getHtmlPageUpdateContacts(Set<Contact> contacts) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>List Contacts</title>\n<style>" +
				"table{\n width: 100%;\nbackground: white;\ncolor: white;\nborder-spacing: 1px;\n}" +
				"td, th{\nbackground: maroon;\npadding: 5px;\n}\n</style>" +
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<p><a href=/addContact>Back</a></p>");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>\n<th>id</th>\n<th>FIO</th>\n");
		stringBuilder.append("<th>Phone</th>\n<th>Email</th>\n<th>Group</th>\n<th>Action</th>");
		for (Contact contact : contacts) {
			stringBuilder.append("<tr>\n<th>");
			stringBuilder.append(contact.getId());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getFio());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getPhone());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getEmail());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(contact.getGroups());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append("<a href='? method=update&id=" + String.valueOf(contact.getId()) + "'>update</a>");
		}
		stringBuilder.append("</table>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
	
	public static StringBuilder getHtmlPageUpdateContact(Contact contact) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"utf-8\">\n<title>Update Contact</title>" +
				"<script>" +
				"function validate() {" +
				"var  valid = true;" +
				"var name = document.getElementById(title);" +
				"if (name.value == ''){" +
				"alert(Введите ФИО);" +
				"valid = false;" +
				"}" +
				"return valid;\n} </script>" +
				
				"<style type=text/css>"
				+ "#centerLayer { width: 400px;\nmargin: 20%;\nbackground: #acff77;\npadding: 10px;} </style>" +
				
				"</head>\n");
		
		stringBuilder.append("<body>\n");
		stringBuilder.append("<div id=centerLayer>");
		stringBuilder.append("<form action=/updateContact method=POST onsubmit=return validate();>");
		stringBuilder.append("New name contact <input type=text id=title name=fio placeholder=" + contact.getFio()+"><br/>");
		stringBuilder.append("New phone contact <input type=text name=phone placeholder=" + contact.getPhone()+"><br/>");
		stringBuilder.append("New email contact <input type=email name=email placeholder=" + contact.getEmail()+"><br/>");
		stringBuilder.append("<input type=submit value=UPDATE>");
		stringBuilder.append("<p><a href=/addContact>Back</a></p>");
		stringBuilder.append("</form>");
		stringBuilder.append("</div>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
	
	public static StringBuilder getHtmlPage(Set<Group> groups) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>List Group</title>\n<style>" +
				"table{\n width: 100%;\nbackground: white;\ncolor: white;\nborder-spacing: 1px;\n}" +
				"td, th{\nbackground: maroon;\npadding: 5px;\n}\n</style>" +
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<p><a href=/addGroup>Back</a></p>");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>\n<th>id</th>\n<th>title</th>\n<th>action</th>");
		for (Group group : groups) {
			stringBuilder.append("<tr>\n<th>");
			stringBuilder.append(group.getId());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(group.getName());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append("<a href='? method=remove&id=" + String.valueOf(group.getId()) + "'>remove</a> ");
		}
		stringBuilder.append("</table>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
	
	public static StringBuilder getHtmlPageUpdate(Group group) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"utf-8\">\n<title>Update Group</title>" +
				"<script>" +
				"function validate() {" +
				"var  valid = true;" +
				"var name = document.getElementById(title);" +
				"if (name.value == ''){" +
				"alert(Введите название группы);" +
				"valid = false;" +
				"}" +
				"return valid;\n} </script>" +
				
				"<style type=text/css>"
				+ "#centerLayer { width: 400px;\nmargin: 20%;\nbackground: #acff77;\npadding: 10px;} </style>" +
				
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<div id=centerLayer>");
		stringBuilder.append("<form action=/updateGroup method=POST onsubmit=return validate();>");
		stringBuilder.append("New name group <input type=text id=title name=title placeholder=" + group.getName() +
				"><br/>");
		stringBuilder.append("<input type=submit value=UPDATE>");
		stringBuilder.append("<p><a href=/addGroup>Back</a></p>");
		stringBuilder.append("</form>");
		stringBuilder.append("</div>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
	
	public static StringBuilder getHtmlPageUpdate(Set<Group> groups) {
		StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n");
		stringBuilder.append("<html lang=\"en\">\n");
		stringBuilder.append("<head>\n<meta charset=\"UTF-8\">\n<title>Update Group</title>\n<style>" +
				"table{\n width: 100%;\nbackground: white;\ncolor: white;\nborder-spacing: 1px;\n}" +
				"td, th{\nbackground: maroon;\npadding: 5px;\n}\n</style>" +
				"</head>\n");
		stringBuilder.append("<body>\n");
		stringBuilder.append("<p><a href=/addGroup>Back</a></p>");
		stringBuilder.append("<table>");
		stringBuilder.append("<tr>\n<th>id</th>\n<th>title</th>\n<th>action</th>");
		for (Group group : groups) {
			stringBuilder.append("<tr>\n<th>");
			stringBuilder.append(group.getId());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append(group.getName());
			stringBuilder.append("</th>\n<th>");
			stringBuilder.append("<a href='? method=update&id=" + String.valueOf(group.getId()) + "'>update</a> ");
		}
		stringBuilder.append("</table>");
		stringBuilder.append("<body>\n");
		stringBuilder.append("</html>");
		
		return stringBuilder;
	}
}
