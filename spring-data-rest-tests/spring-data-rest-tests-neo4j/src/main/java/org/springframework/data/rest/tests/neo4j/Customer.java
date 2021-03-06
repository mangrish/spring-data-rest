/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.rest.tests.neo4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.util.Assert;

/**
 * @author Mark Angrish
 */
@NodeEntity
public class Customer {

	@GraphId
	private Long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	@Relationship(type = "ADDRESS")
	private Set<Address> addresses = new HashSet<Address>();

	public Customer(String firstName, String lastName, String emailAddress) {

		Assert.hasText(firstName);
		Assert.hasText(lastName);
		Assert.hasText(emailAddress);

		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	protected Customer() {

	}

	public void add(Address address) {
		this.addresses.add(address);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Address> getAddresses() {
		return Collections.unmodifiableSet(addresses);
	}

	public boolean hasAddress(Address address) {
		return addresses.contains(address);
	}

	@Override
	public String toString() {
		return String.format("%s %s <%s>", firstName, lastName, emailAddress);
	}
}
