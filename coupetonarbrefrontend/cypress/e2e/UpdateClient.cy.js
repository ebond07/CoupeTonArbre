describe('Update Client Admin Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/accounts');
  });

  it('should update client information', () => {
    // Click on the "Edit" button for a specific client
    cy.get('button:contains("Edit")').first().click();

    // Verify that the update form is displayed and contains the correct data
    cy.get('.update-client-form').should('be.visible');
    cy.get('input[name="firstName"]').should('have.value', 'Alick'); // Adjust based on your initial data
    // Add more assertions for other fields

    // Update client information
    cy.get('input[name="firstName"]').clear().type('UpdatedFirstName');
    // Add more commands to update other fields if needed

    // Submit the form
    cy.get('button:contains("Update Client")').click();

    // Verify that the client information has been updated
    cy.url().should('include', '/accounts'); // Assuming successful update redirects to the Accounts page
    cy.get('td:contains("UpdatedFirstName")').should('exist');
  });
});
