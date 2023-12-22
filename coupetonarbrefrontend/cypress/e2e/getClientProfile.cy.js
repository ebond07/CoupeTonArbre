describe('Profile Page', () => {
    it('should get the client profile information', () => {

    
      cy.visit('http://localhost:3000');

  
      // Set the authentication state to true
      cy.clientLogin()
  
  
     
  
      // Visit the profile page again after authentication
      cy.visit('http://localhost:3000/profile');
  
      // Wait for the checkProfile request to complete
  
      // Check if the user info is displayed after submission
      cy.get('.info-text').should('have.length', 5); // Assuming there are 5 info fields
  
      // Check if the user's name is displayed correctly
      cy.get('#firstNameText').should('contain.text', 'John');
      cy.get('#lastNameText').should('contain.text', 'Doe');
  
      // Check if the user's email is displayed correctly
      cy.get('#emailText').should('contain.text', 'john.doe@example.com');
  
      // Check if the user's phone number is displayed correctly
      cy.get('#phoneNumberText').should('contain.text', '1234567890');
  
      // Check if the user's address is displayed correctly
      cy.get('#addressText').should('contain.text', '123 Main St');
    });
  });
  