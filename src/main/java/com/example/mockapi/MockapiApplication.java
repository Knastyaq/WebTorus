import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/app/v1")
public class AppController {

	@GetMapping("/getRequest")
	public ResponseEntity<String> getRequest(@RequestParam int id, @RequestParam String name) {
		if (id <= 10 || name.length() <= 5) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Invalid parameters: id must be greater than 10 and name length must be greater than 5.");
		}


		try {
			Thread.sleep((id > 10 && id < 50) ? 1000 : 500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}


		try {
			String responseBody = new String(Files.readAllBytes(Paths.get("getAnswer.txt")));
			responseBody = responseBody.replace("{name}", name);
			return ResponseEntity.ok(responseBody);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error reading response file.");
		}
	}

	@PostMapping("/postRequest")
	public ResponseEntity<String> postRequest(@RequestBody RequestBody body) {
		if (body.getName() == null || body.getSurname() == null || body.getAge() == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Invalid parameters: name, surname, and age must not be empty.");
		}


		try {
			String responseBody = new String(Files.readAllBytes(Paths.get("postAnswer.txt")));
			responseBody = responseBody.replace("{name}", body.getName())
					.replace("{surname}", body.getSurname())
					.replace("{age}", String.valueOf(body.getAge()));
			return ResponseEntity.ok(responseBody);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error reading response file.");
		}
	}

	static class RequestBody {
		private String name;
		private String surname;
		private Integer age;

		// Getters and Setters
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String getSurname() { return surname; }
		public void setSurname(String surname) { this.surname = surname; }
		public Integer getAge() { return age; }
		public void setAge(Integer age) { this.age = age; }
	}
}