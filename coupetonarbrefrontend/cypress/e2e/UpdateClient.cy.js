describe('Update Client Admin Page', () => {
  beforeEach(() => {
    
    cy.visit('localhost:3000');

    // Verify that the Navbar is rendered
    cy.get('#nav-container').should('exist');
    cy.get('nav').should('exist');

    // Verify the presence of welcome message and content
    cy.contains('Welcome to the Home Page').should('exist');
    cy.contains('This is the content of the home page.').should('exist');

    // Verify the Navbar links
    cy.get('nav').contains('Home').should('exist');
    // cy.get('nav').contains('Appointments').should('exist');
    // cy.get('nav').contains('Quote Requests').should('exist');
    // cy.get('nav').contains('Accounts').should('exist');
    // cy.get('nav').contains('Feedback').should('exist');
    cy.get('nav').contains('Login').should('exist');




    cy.adminLogin()
 

  cy.get('nav').contains('Logout').should('exist');

  cy.get('nav').contains('Accounts').click();
});

  it('should update client information', () => {
    // Click on the "Edit" button for a specific client
    cy.get('button:contains("Edit")').first().click();

    // Verify that the update form is displayed and contains the correct data
    cy.get('.update-client-form').should('be.visible');
    cy.get('input[name="firstName"]').should('have.value', 'Alex'); // Adjust based on your initial data
    // Add more assertions for other fields

    // Update client information
    cy.get('input[name="firstName"]').clear().type('UpdatedFirstName');
    // Add more commands to update other fields if needed

    // Submit the form
    cy.get('button:contains("Update Client")').click();

    // Verify that the client information has been updated
    cy.url().should('include', '/admin/accounts'); // Assuming successful update redirects to the Accounts page
    cy.get('td:contains("UpdatedFirstName")').should('exist');
  });
});
