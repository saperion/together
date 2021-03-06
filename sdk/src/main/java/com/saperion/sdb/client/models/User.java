package com.saperion.sdb.client.models;

import java.util.Collection;

import com.saperion.sdb.client.Together;
import com.saperion.sdb.client.exceptions.AuthenticationException;
import com.saperion.sdb.client.exceptions.ConnectException;
import com.saperion.sdb.spi.rights.UserRight;
import com.saperion.sdb.spi.states.UserState;

public class User extends Item<User, com.saperion.sdb.rs.models.User>{
	public User(Together together) {
		this(new com.saperion.sdb.rs.models.User(), together);
	}
	
	public User(com.saperion.sdb.rs.models.User user, Together together) {
		super(user, "/users", together);
	}
		
	public void recycle() {
		delegate.addState(UserState.RECYCLED);
	}
	
	public void restore() {
		delegate.removeState(UserState.RECYCLED);
	}
	
	public StreamResult avatar() throws AuthenticationException, ConnectException{
		return together.get(resource+"/"+getId()+"/avatar", "image/png", StreamResult.class);
	}
	
	public void avatar(StreamResult stream) throws AuthenticationException, ConnectException{
		together.update(resource+"/"+getId()+"/avatar", stream);
	}
	
	@Override
	public ClientModelType getType() {
		return ClientModelType.USER;
	}
	
	//delegates
	public String getName() {
		return delegate.getDisplayname();
	}

	public User setName(String name) {
		delegate.setDisplayname(name);
		return this;
	}

	public String getEmail() {
		return delegate.getEmail();
	}

	public User setEmail(String email) {
		delegate.setEmail(email);
		return this;
	}

	public String getCompany() {
		return delegate.getCompany();
	}

	public boolean isGuest() {
		return delegate.getStates().contains(UserState.GUEST);
	}

	public boolean isInRecycleBin() {
		return delegate.getStates().contains(UserState.RECYCLED);
	}

	public void setInRecycleBin(boolean inRecycleBin) {
		if (inRecycleBin){
			recycle();
		} else {
			restore();
		}
	}

	public User setRights(Collection<UserRight> userRights) {
		delegate.setRights(userRights);
		return this;
	}

	public User addRight(UserRight right) {
		delegate.addRight(right);
		return this;
	}

	public User removeRight(UserRight right) {
		delegate.removeRight(right);
		return this;
	}

	public Collection<UserRight> getRights() {
		return delegate.getRights();
	}

	@Override
	public String toString() {
		return "User [getName()=" + getName() + ", getEmail()=" + getEmail() + ", getCompany()="
				+ getCompany() + ", isGuest()=" + isGuest() + ", isInRecycleBin()="
				+ isInRecycleBin() + ", getRights()=" + getRights() + ", getId()=" + getId() + "]";
	}
}
