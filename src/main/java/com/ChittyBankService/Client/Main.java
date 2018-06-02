package com.ChittyBankService.Client;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ChittyBankService.ChittyChittyBankApplication;
import com.ChittyBankService.Model.Account;
import com.ChittyBankService.Model.Bank;
import com.ChittyBankService.Model.Branch;
import com.ChittyBankService.Model.Customer;
import com.ChittyBankService.Model.Employee;
import com.ChittyBankService.Model.Ticker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Main extends Application {
	static Stage stage;
	static BorderPane Layout;

	public static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(Main.class).profiles("client").run(args);
		launch(args);
	}

	public static Bank restPost() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		Bank bank = restTemplate.postForObject("http://10.0.2.2:8080/bank", entity, Bank.class);

		return bank;
	}

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		showStage("View/MainWindow.fxml", "Chitty Chitty Bank");
		loadView("View/LoginView.fxml");
	}

	public void showStage(String resource, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(resource));
		Layout = loader.load();
		Scene scene = new Scene(Layout);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	public static void showDialog(String resource, String title) throws IOException {
		// @TODO move string parameters to enum class
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(resource));
		Stage dialog = new Stage();
		Scene scene = new Scene(loader.load());
		dialog.setTitle(title);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(stage);
		dialog.setScene(scene);
		dialog.showAndWait();
	}

	public static void loadView(String resource) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(resource));
		Layout.setCenter(loader.load());
	}

	public static void postTransaction(Account account) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Account> entity = new HttpEntity<Account>(account, headers);
		Account account1 = restTemplate.postForObject("http://10.0.2.2:8080/account/save", entity, Account.class);

	}

	public static void postCustomer(Customer customer) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
		Customer cResponse = restTemplate.postForObject("http://10.0.2.2:8080/customer/save", entity, Customer.class);

	}

	public static void postBranch(Branch branch) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Branch> entity = new HttpEntity<Branch>(branch, headers);
		Branch bResponse = restTemplate.postForObject("http://10.0.2.2:8080/branch/save", entity, Branch.class);
	}

	public static Ticker[] getTickers() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity<>(headers);
		Ticker[] tickers = restTemplate.postForObject("http://10.0.2.2:8080/ticker", entity, Ticker[].class);
		return tickers;
	}

}
