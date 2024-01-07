// cypress/integration/createQuoteRequestAdmin.spec.js

describe('Create Quote Request Admin Page', () => {
    beforeEach(() => {
      // Assuming your app runs on localhost:3000
      cy.visit('http://localhost:3000');
      cy.adminLogin()
      cy.visit('http://localhost:3000/admin/newQuoteRequest');
    });
  
    it('should fill in the form and submit', () => {
      // Select a user by filtering and choosing from the dropdown
      cy.contains('Select a client').click({ force: true });
      cy.get('#client-select').should('be.visible').select('Alick Ucceli', { force: true });
  
      // Fill in the form
      cy.get('[name="description"]').type('Sample description');
      cy.get('[name="service"]').select('Tree Trimming'); // Replace with a valid service
      cy.get('.form-date').click(); // Opens the date picker
      cy.get('.react-datepicker__day--today').click(); // Selects today's date
      cy.get('.form-time').click(); // Opens the time picker
      cy.get('.rc-time-picker-panel-input').type('4:00 PM'); // Replace with a valid time
  
      // Submit the form
      cy.get('.form-button').click( {force: true} );
  
      // Wait for the request to complete (modify this based on your app's behavior)
      cy.wait(2000); // Wait for 2 seconds (modify as needed)
  
     // Replace with your success page URL
    });
  });
  