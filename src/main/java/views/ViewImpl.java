package views;


import controller.ControllerImpl;
import models.Contact;
import models.Group;
import models.User;
import utilits.ConsoleReader;
import utilits.TeamList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *Отображение
 */

public class ViewImpl implements View {
	
	
	private ConsoleReader consoleReader = new ConsoleReader();
	private ControllerImpl controller = new ControllerImpl();
	
	public ViewImpl() {
	
	}
	
	@Override
	public void actionContact() {
		String command = this.consoleReader.readString();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
			addContact("con");
		if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
			removeContact();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
			updateContact();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
			getContacts();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
			appGroupContact();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
			removeGroupContact();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
			getContactInfo();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.ser)))
			searchName();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
			startPage();
		pageActionContact();
	}//***
	
	@Override
	public void actionGroup() {
		String command = this.consoleReader.readString();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
			addGroup("gro");
		if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
			removeGroup();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
			updateGroup();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
			getGroups();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
			getContactsGroup();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
			startPage();
		System.out.println("Не поддерживатеся");
		pageActionGroup();
	}//***
	
	@Override
	public void addContact(String command) {
		List<String> attContact = new ArrayList<>();
			attContact.add(0,getNameContact());
			System.out.println("Введите телефон");
			attContact.add(1,this.consoleReader.readString());
			System.out.println("Введите email");
			attContact.add(2,this.consoleReader.readString());
			attContact.add(3,"нет группы");
		try {
			this.controller.addEntity(attContact,command);
			getSuc();
		} catch (IOException e) {
			System.out.println("Не удалось создать");
		} catch (SQLException e) {
			failed();
		}
		
	}//***
	
	@Override
	public void updateContact() {
		List<String> attContact = new ArrayList<>();
		getContacts();
		attContact.add(0,getNameContact());
		try {
			if (this.controller.existContact(attContact.get(0))) {
				System.out.println("Введите новое Ф И О");
				attContact.add(1, getNameContact());
					System.out.println("Введите новый телефон");
					String newPhone = this.consoleReader.readString();
					if (newPhone.trim().length() > 0){
						attContact.add(2,newPhone);
					}else {
						attContact.add(2,"nop");
						
					}
					System.out.println("Введите новый email");
					String newEmail = this.consoleReader.readString();
					if (newEmail.trim().length() > 0){
						attContact.add(3,newEmail);
					}else {
						attContact.add(3,"noe");
					}
					this.controller.updateContact(attContact);
					getSuc();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//***
	
	@Override
	public void getContacts() {
		Set<Contact> contacts = null;
		try {
			contacts = this.controller.getContacts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Contact contact : contacts){
			System.out.println(contact.contactInf());
			if (!contact.getGroups().isEmpty()){
				System.out.println(contact.getGroups());
			}
		}
	}//***
	
	@Override
	public void getGroups() {
		getGroupsUtilit();
		pageActionGroup();
	}//***
	
	@Override
	public void getContactInfo() {
		getContacts();
		Contact contact = null;
		try {
			contact = this.controller.getContact(getNameContact());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(contact);
	}//***
	
	@Override
	public String getNameContact() {
		System.out.println("Введите Ф И О контакта (обязательное поле)");
		String name = this.consoleReader.readString();
		if (name.trim().length() > 0){
			return name;
		}else {
			emptyLine();
			pageActionContact();
		}
		return null;
	}//***
	
	@Override
	public String getNameGroup() {
		System.out.println("Введите название группы");
		String name = this.consoleReader.readString();
		if (name.trim().length() > 0){
			return name;
		}else {
			emptyLine();
			pageActionGroup();
		}
		return null;
	}//***
	
	@Override
	public void searchName() {
		Contact contact = null;
		try {
			contact = this.controller.getContact(getNameContact());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (contact != null){
			System.out.println(contact);
		}else {
			notFound();
		}
		pageActionContact();
	}//***
	
	@Override
	public void startPage() {
		while (true) {
			System.out.println("==========================================\n");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Стартовая страница \n");
			stringBuilder.append("Действие с контактом введите         --con\n");
			stringBuilder.append("Действие с группой контактов введите --gro\n");
			stringBuilder.append("Аналитическая информация             --an\n");
			stringBuilder.append("Для выхода введите                   --exit\n");
			System.out.println(stringBuilder);
			System.out.println("==========================================\n");
			String command = this.consoleReader.readString();
			if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
				pageActionContact();
			if ((command.equalsIgnoreCase(String.valueOf(TeamList.gro))))
				pageActionGroup();
			if ((command.equalsIgnoreCase(String.valueOf(TeamList.an))))
				pageAnalyticalInf();
			if ((command.equalsIgnoreCase(String.valueOf(TeamList.exit))))
				System.exit(0);
			System.out.println("Команда не поддерживается");
		}
	}//***
	
	@Override
	public void pageActionGroup() {
		StringBuilder stringBuilder = new StringBuilder("Выберите действие для группы\n");
		stringBuilder.append("Создать группу                      --add\n");
		stringBuilder.append("Удалить группу                      --rem\n");
		stringBuilder.append("Редактировать группу                --upd\n");
		stringBuilder.append("Список групп                        --list\n");
		stringBuilder.append("Показать список контактов группы    --listc\n");
		stringBuilder.append("Вверх                               --up\n");
		System.out.println(stringBuilder);
		System.out.println("==========================================\n");
		actionGroup();
	}//***
	
	@Override
	public void pageActionContact() {
		StringBuilder stringBuilder = new StringBuilder("Выберите действие для контакта\n");
		stringBuilder.append("Добавить контакт                --add\n");
		stringBuilder.append("Удалить контакт                 --rem\n");
		stringBuilder.append("Найти контакт                   --ser\n");
		stringBuilder.append("Редактировать контакт           --upd\n");
		stringBuilder.append("Показать список контактов       --list\n");
		stringBuilder.append("Добавить контакт в группу       --addg\n");
		stringBuilder.append("Удалить контакт из группы       --remg\n");
		stringBuilder.append("Показать информацию о контакте  --inf\n");
		stringBuilder.append("Вверх                           --up\n");
		System.out.println(stringBuilder);
		System.out.println("==========================================\n");
		actionContact();
	}//***
	
	@Override
	public void getNoGroup() {
		System.out.println("нет группы");
	}
	
	@Override
	public void getGroupsUtilit() {
		Set<Group> groups = this.controller.getGroups();
		for (Group group : groups){
			System.out.println(group);
		}
	}
	
	@Override
	public void getSuc() {
		System.out.println("успешно");
	}
	
	@Override
	public void emptyLine() {
		System.out.println("пустая строка");
	}
	
	@Override
	public void notFound() {
		System.out.println("не найденно");
	}
	
	@Override
	public void failed() {
		System.out.println("не удалось");
	}
	
	@Override
	public void authorizationPage() {
		System.out.println("Страница авторизации");
		List<String> attr = new ArrayList<>();
		attr.add(0,"Филип");
		attr.add(1,"root");
//		attr.add(0,getNameUser());
//		attr.add(1,getPasswordUser());
		
		User user = null;
		try {
			user = this.controller.authorizationPage(attr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user!= null && user.getAct()){
			startPage();
		}else {
			System.out.println("user отсутствует");
			authorizationPage();
		}
	}
	
	@Override
	public void removeContact() {
		getContacts();
		String name = getNameContact();
		try {
			if (this.controller.existContact(name)){
				this.controller.removeContact(name);
				getSuc();
			}else {
				notFound();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//***
	
	@Override
	public void appGroupContact() {
		List<String> attContact = new ArrayList<>();
		getContacts();
		attContact.add(0,getNameContact());
		try {
			if (this.controller.existContact(attContact.get(0))){
				getGroupsUtilit();
				attContact.add(1,getNameGroup());
				try {
					if (this.controller.existGroup(attContact.get(1))){
						this.controller.appGroupContact(attContact);
						getSuc();
					}
				} catch (SQLException e) {
					failed();
				}
			}else {
				System.out.println("нет контакта");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//***
	
	@Override
	public void removeGroupContact() {
		List<String> attr = new ArrayList<>();
		getContacts();
		attr.add(0,getNameContact());
		try {
			if (this.controller.existContact(attr.get(0))){
				getGroupsUtilit();
				attr.add(1,getNameGroup());
				if (this.controller.existGroup(attr.get(1))){
					this.controller.removeGroupContact(attr);
					getSuc();
				}else {
					System.out.println("нет группы");
				}
			}else {
				System.out.println("нет контакта");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//***
	
	@Override
	public void addGroup(String command) {
		List<String> attGroup = new ArrayList<>();
		attGroup.add(0,getNameGroup());
		try {
			this.controller.addEntity(attGroup,command);
		} catch (IOException e) {
			System.out.println("не удачно");
		} catch (SQLException e) {
			failed();
		}
		pageActionGroup();
	}//***
	
	@Override
	public void removeGroup() {
		getGroupsUtilit();
		try {
			if (controller.removeGroup(getNameGroup())){
				getSuc();
			}else {
				getNoGroup();
			}
		} catch (SQLException e) {
			failed();
		}
		pageActionGroup();
	}//***
	
	@Override
	public void updateGroup() {
		getGroupsUtilit();
		List<String> attGroup = new ArrayList<>();
		attGroup.add(0,getNameGroup().trim());
		attGroup.add(1,getNameNewGroup().trim());
		try {
				if (this.controller.updateGroup(attGroup)){
					getSuc();
				}else {
					getNoGroup();
				}
		} catch (SQLException e) {
			failed();
		}
		pageActionGroup();
	}//***
	
	@Override
	public void getContactsGroup() {
		getGroupsUtilit();
		String name = getNameGroup();
		try {
			if (this.controller.existGroup(name)){
				Set<Contact> contacts = this.controller.getContactsGroup(name);
//				System.out.println(contacts);
				if (!contacts.isEmpty()){
					for (Contact contact : contacts){
						System.out.println(contact.contactInf());
					}
				}else {
					System.out.println("нет контактов");
				}
			}
		} catch (SQLException e) {
			failed();
		}
		pageActionGroup();
	}//***
	
	@Override
	public void numberUsers() {
		try {
			System.out.println("Кол-во пользователей " + this.controller.numberUsers());
		} catch (SQLException e) {
			failed();
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void numberContacts() {
		try {
			System.out.println("Кол-во контактов " + this.controller.numberContacts(getNameUser()));
		} catch (SQLException e) {
			failed();
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void quantityGroupsUser() {
		try {
			System.out.println("Кол-во групп " + this.controller.quantityGroupsUser(getNameUser()));
		} catch (SQLException e) {
			failed();
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void averageNumberContactsGroups() {
		try {
			System.out.println("Среднее количество контактов в группах "
					+this.controller.averageNumberContactsGroups());
		} catch (SQLException e) {
			failed();
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void averageNumberContactsUser() {
		try {
			System.out.println("Среднее количество контактов у пользователя "
					+this.controller.averageNumberContactsUser());
		} catch (SQLException e) {
			failed();
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void userWithContactsMin_10() {
		Set<User> users = null;
		try {
			users = this.controller.userWithContactsMin_10();
		} catch (SQLException e) {
			failed();
		}
		System.out.println("Пользователь с количеством контактов < 10 ");
		for (User user : users){
			System.out.println(user.getLogin());
		}
		pageAnalyticalInf();
	}
	
	@Override
	public void pageAnalyticalInf() {
		StringBuilder stringBuilder = new StringBuilder("Аналитическая информация\n");
		stringBuilder.append("Кол-во контактов каждого users     --ncon\n");
		stringBuilder.append("Кол-во групп каждого users         --grus\n");
		stringBuilder.append("Кол-во контактов < 10              --min10\n");
		stringBuilder.append("Среднее число контактов в группе   --avrg\n");
		stringBuilder.append("Среднее число контактов у user     --avru\n");
		stringBuilder.append("Общее кол-во users                 --users\n");
		stringBuilder.append("Вверх                              --up\n");
		System.out.println(stringBuilder);
		System.out.println("==========================================\n");
		actionAnalyticalInf();
	}//***
	
	@Override
	public void actionAnalyticalInf() {
		String command = this.consoleReader.readString();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.ncon)))
			numberContacts();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.grus)))
			quantityGroupsUser();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.min10)))
			userWithContactsMin_10();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.avrg)))
			averageNumberContactsGroups();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.avru)))
			averageNumberContactsUser();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.users)))
			numberUsers();
		if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
			startPage();
		System.out.println("Не actionAnalyticalInf поддерживатеся");
		pageAnalyticalInf();
	}
	
	@Override
	public String getNameUser() {
		System.out.println("Введите имя пользователя (обязательное поле)");
		String name = this.consoleReader.readString();
		if (name.trim().length() > 0){
			return name;
		}else {
			emptyLine();
			pageActionGroup();
		}
		return null;
	}
	
	@Override
	public String getPasswordUser() {
		System.out.println("Введите password пользователя (обязательное поле)");
		String name = this.consoleReader.readString();
		if (name.trim().length() > 0){
			return name;
		}else {
			emptyLine();
			pageActionGroup();
		}
		return null;
	}
	
	@Override
	public String getNameNewGroup() {
		System.out.println("Введите новое название для группы");
		String name = this.consoleReader.readString();
		if (name.trim().length() > 0){
			return name ;
		}else {
			emptyLine();
			pageActionGroup();
		}
		return null;
	}
	
}
