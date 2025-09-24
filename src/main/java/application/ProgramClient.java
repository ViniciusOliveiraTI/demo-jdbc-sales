package application;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

import java.util.List;

public class ProgramClient {

    public static void main(String[] args) {

        ClientDao clientDao = DaoFactory.createClientDao();

//        System.out.println("==== TEST 1 client insert ====");
//        Client client = new Client(null, "Pedro Ruiz", "pedro@gmail.com", "19998122243", "Mogi Guaçu");
//        clientDao.insert(client);
//        System.out.println("Client inserted successfuly");
//
//        System.out.println("==== TEST 2 client update ====");
//        client.setName("Jhon Marston");
//        clientDao.update(client);
//        System.out.println("Client updated successfuly");

        System.out.println("==== TEST 3 client findById ====");
        System.out.println(clientDao.findById(1));

        System.out.println("==== TEST 4 client findAll ====");
        List<Client> clients = clientDao.findAll();
        clients.forEach(System.out::println);

//        System.out.println("==== TEST 5 client deleteById ====");
//        clientDao.deleteById(3);
//        System.out.println("Client deleted!");

    }

}
