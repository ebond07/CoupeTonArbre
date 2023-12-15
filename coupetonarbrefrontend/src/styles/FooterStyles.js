import styled from 'styled-components';
import BebasNeueTTF from './fonts/BebasNeue-Regular.ttf';
export const Box = styled.div`
	padding: 2.5% 2.5% 0 2.5%;;
	background: black;
	// position: absolute;
	bottom: 0;
	width: 95%;

	@media (max-width: 1000px) {
		// padding: 70px 30px;
	}

	@font-face {
		font-family: 'Bebas Neue';
		src: url(${BebasNeueTTF}) format('truetype');
		font-weight: normal;
		font-style: normal;
	  }
	
	  body {
		font-family: 'Bebas Neue', sans-serif;
		margin: 0;
		padding: 0;
	  }
`;

export const FooterContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	max-width: 1000px;
	margin: 0 auto;
	/* background: red; */
`;

export const Column = styled.div`
	display: flex;
	flex-direction: column;
	text-align: left;
	margin-left: 60px;
`;

export const Row = styled.div`
	display: grid;
	grid-template-columns: repeat(
		auto-fill,
		minmax(170px, 1fr)
	);
	grid-gap: 20px;

	@media (max-width: 1000px) {
		grid-template-columns: repeat(
			auto-fill,
			minmax(200px, 1fr)
		);
	}
`;

export const FooterLink = styled.a`
	color: #fff;
	margin-bottom: 20px;
	font-size: 18px;
	text-decoration: none;

	&:hover {
		color: green;
		transition: 200ms ease-in;
	}
`;

export const Heading = styled.p`
	font-size: 24px;
	color: #fff;
	margin-bottom: 40px;
	font-weight: bold;
`;
