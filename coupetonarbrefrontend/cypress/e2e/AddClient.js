describe('AddClientTest', () => {
    it('allows an admin to add a new client', () => {
      // Visit the AccountsAdmin page
      cy.visit('localhost:3000/accounts');
  
      // Check for the presence of the "Add Client" button and click it
      cy.contains('Add Client').should('exist').click();
  
      // Fill out the form fields with client information
      cy.get('input[name="firstName"]').type('John');
      cy.get('input[name="lastName"]').type('Doe');
      cy.get('input[name="email"]').type('john.doe@example.com');
      cy.get('input[name="phoneNumber"]').type('123-456-7890');
      cy.get('input[name="address"]').type('123 Main St');
  
      // Submit the form
      cy.contains('Submit New Client').click();
  
      // Verify that the new client appears in the client list
      cy.contains('John Doe').should('exist');
      cy.contains('john.doe@example.com').should('exist');
      cy.contains('123-456-7890').should('exist');
      cy.contains('123 Main St').should('exist');
  
      // Optionally, verify that the form is no longer visible
      cy.contains('Submit New Client').should('not.exist');
    });
  });
  