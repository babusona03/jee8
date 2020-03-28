package dev.ls.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import dev.ls.entity.UserCredentials;

@Transactional
public class UserCredentialService {
	@PersistenceContext
	EntityManager entityManager;
	
	public UserCredentials createCredential(UserCredentials userCredentials) {
		entityManager.persist(userCredentials);
		return userCredentials;
	}
	public UserCredentials updateCredential(UserCredentials userCredentials) {
		entityManager.merge(userCredentials);
		return userCredentials;
	}
	public UserCredentials findCredentialById(Integer id) {
		return entityManager.find(UserCredentials.class, id);
	}
	public List<UserCredentials> getAllCredentials() {
		return entityManager.createQuery("select e from UserCredentials e",UserCredentials.class).getResultList();
	}
	public void deleteCredential(UserCredentials userCredentials) {
		entityManager.remove(userCredentials);
	}
}
