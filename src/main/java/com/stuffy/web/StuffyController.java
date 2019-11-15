package com.stuffy.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stuffy.business.Stuffy;
import com.stuffy.db.StuffyRepository;

@CrossOrigin
@RestController
@RequestMapping("/stuffies")
public class StuffyController {
	
	@Autowired
	private StuffyRepository stuffyRepo;
	
	@GetMapping("/")
	public Iterable<Stuffy> listStuffies() {
		return stuffyRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Stuffy> getStuffy(@PathVariable int id) {
		return stuffyRepo.findById(id);
	}

	// wrap the stuffy inside a JsonResponse
	@PostMapping("/")
	public JsonResponse addStuffy(@RequestBody Stuffy s) {
		JsonResponse jr = null;
		try {
			//happy path...  save method worked!
			// create an instance of JsonResponse that stores
			// the Stuffy in data
			jr = JsonResponse.getInstance(stuffyRepo.save(s));
		} 
		catch (Exception e) {
			// something bad happened
			// create an instance of JsonResponse that stores
			// an error message and prints stack trace
			e.printStackTrace();
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}
