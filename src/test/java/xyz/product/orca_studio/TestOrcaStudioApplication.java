package xyz.product.orca_studio;

import org.springframework.boot.SpringApplication;

public class TestOrcaStudioApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrcaStudioApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
