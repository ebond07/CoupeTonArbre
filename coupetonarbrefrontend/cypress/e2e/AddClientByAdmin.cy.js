describe('AddClientByAdminTest', () => {
  it('allows an admin to add a new client', () => {
    // Visit the AccountsAdmin page
    cy.visit('localhost:3000/accounts');

    // Check for the presence of the "Add Client" button and click it
    cy.contains('Add Client').should('exist').click();

    // Fill out the form fields with client information
    cy.get('input[name="firstName"]').type('Da Zhuo');
    cy.get('input[name="lastName"]').type('Xie');
    cy.get('input[name="email"]').type('dazhuoxie1024@gmail.com');
    cy.get('input[name="phoneNumber"]').type('(514) 550-6578');
    cy.get('input[name="address"]').type('1175 rue Rembrandt, Brossard, Québec, Canada');

    // Submit the form
    cy.contains('Submit New Client').click();

    // Verify that the new client appears in the client list
    cy.contains('Da Zhuo').should('exist');
    cy.contains('Xie').should('exist');
    cy.contains('dazhuoxie1024@gmail.com').should('exist');
    cy.contains('(514) 550-6578').should('exist');
    cy.contains('1175 rue Rembrandt, Brossard, Québec, Canada').should('exist');

    // Optionally, verify that the form is no longer visible
    cy.contains('Submit New Client').should('not.exist');
  });
});
