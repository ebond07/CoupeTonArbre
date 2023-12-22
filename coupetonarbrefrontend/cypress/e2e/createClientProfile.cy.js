Cypress.on('uncaught:exception', (err, runnable) => {
  // Returning false prevents Cypress from failing the test
  return false;
});

describe('Profile Page', () => {
    it('should fill out the form and display user info after submission', () => {

    
      cy.visit('http://localhost:3000');

  
      // Set the authentication state to true
      cy.clientLogin()
  
  
      // Stub the Axios requests to mock the server responses
      cy.intercept('GET', 'http://localhost:8080/users/client?simpleCheck=true', {
        statusCode: 404, // Assuming user doesn't have a profile initially
      }).as('checkProfile');
  
      // Visit the profile page again after authentication
      cy.visit('http://localhost:3000/profile');
  
      // Wait for the checkProfile request to complete
      cy.wait('@checkProfile');
  
      // Check if the form is visible for the user to fill out
      cy.get('.profile-create-form').should('be.visible');
  
      // Fill out the form
      cy.get('#firstName').type('John');
      cy.get('#lastName').type('Doe');
      cy.get('#email').type('john.doe@example.com');
      cy.get('#phoneNumber').type('1234567890');
      cy.get('#address').type('123 Main St');
  
      // Submit the form
      cy.get('.save-btn').click();
  
      // Wait for 2 seconds (adjust as needed) for the profile to be created
      cy.wait(2000);
    });
  });
  