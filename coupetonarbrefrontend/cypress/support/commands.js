// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

Cypress.Commands.add('adminLogin', (overrides = {}) => {
   
    cy.get('nav').contains('Login').click();

    cy.origin('https://dev-4hcoszrtvq0f1jnk.us.auth0.com', () => {
    cy.get('#username').type('evanbond007@outlook.com'); 
    cy.get('#password').type('EvanPassword123'); 
    
    cy.get('button:contains("Continue")').first().click({force : true});
 
 
    cy.visit('localhost:3000');
})
  });

  Cypress.Commands.add('clientLogin', (overrides = {}) => {
   
    cy.get('nav').contains('Login').click();

    cy.origin('https://dev-4hcoszrtvq0f1jnk.us.auth0.com', () => {
    cy.get('#username').type('davidrallo28@outlook.com'); 
    cy.get('#password').type('ClientPassword123'); 
    
    cy.get('button:contains("Continue")').first().click({force : true});
 
 
    cy.visit('localhost:3000');
})
  });