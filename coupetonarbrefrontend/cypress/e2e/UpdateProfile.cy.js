Cypress.on('uncaught:exception', (err, runnable) => {
    // Returning false prevents Cypress from failing the test
    return false;
  });
  
  
  describe('Update Profile Page', () => {
    beforeEach(() => {
      
      cy.visit('localhost:3000');
  
      cy.get('#nav-container').should('exist');
      cy.get('nav').should('exist');
  
      cy.contains('4 Steps Until The Job Is Done').should('exist');
  
  
      cy.get('nav').contains('Home').should('exist');

      cy.get('nav').contains('Login').should('exist');
  
  
  
  
      cy.clientLogin()
   
  
    cy.get('nav').contains('Logout').should('exist');
  
  
  });
  
    it('should update profile information', () => {

        cy.get('nav').contains('Profile').click();
      cy.get('button:contains("Update Your Information")').first().click();
  
      cy.get('.update-client-form').should('be.visible');
      cy.get('input[name="firstName"]').should('have.value', 'John'); 
  
      // Update client information
      cy.get('input[name="firstName"]').clear().type('UpdatedFirstName');
  
      // Submit the form
      cy.get('button:contains("Save")').click();
  
      // Verify that the client information has been updated
      cy.url().should('include', '/profile'); 
      cy.get('#firstNameText').should('contain.text', 'UpdatedFirstName');
    });
  });
  