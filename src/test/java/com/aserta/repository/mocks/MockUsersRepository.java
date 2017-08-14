package com.aserta.repository.mocks;

import com.aserta.data.interfaces.IUsersRepository;

public class MockUsersRepository implements IUsersRepository {

	@Override
	public boolean exists(String email) {
		if(email.equals("no.existe@bside.com.mx")) {
			return true;			
		}
		else {
			return false;
		}
	}

}