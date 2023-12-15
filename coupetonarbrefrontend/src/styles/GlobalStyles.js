import { createGlobalStyle } from 'styled-components';
import PoppinsMediumTTF from './fonts/Poppins-Medium.ttf';

const GlobalStyles = createGlobalStyle`
  @font-face {
    font-family: 'Poppins';
    src: url(${PoppinsMediumTTF}) format('truetype');
    font-weight: normal;
    font-style: normal;
  }

  body {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
  }
`;

export default GlobalStyles;
