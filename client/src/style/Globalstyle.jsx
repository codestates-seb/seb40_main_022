import { createGlobalStyle } from 'styled-components';

const variables = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  :root{
    --font-11 : 11px;
    --font-12 : 12px;
    --font-13 : 13px;
    --font-14 : 14px;
    --font-15 : 15px;
    --font-16 : 16px;
    --font-17 : 17px;
    --font-18 : 18px;
    --font-19 : 19px;
    --font-20 : 20px;
    --font-21 : 21px;
    --font-24 : 24px;
    --font-27 : 27px;
    --font-28 : 28px;
    --font-30 : 30px;
    --logored : #FC6666;
    --boxblue : #82CBC4;
    --tagyellow : #FCDDB0;
    --buttongray : #AEAEAE;
    --footerblack : #232629;
    --backgreen : #D6E3E3;
    --headgray : #626262;
    --backwhite : #FDFDFD; 
    --white: hsl(0,0%,100%);
    --black-025: hsl(210, 8%, 97.5%);
    --black-050: hsl(210, 8%, 95%);
    --black-075: hsl(210, 8%, 90%);
    --black-100: hsl(210, 8%, 85%);
    --black-150: hsl(210, 8%, 80%);
    --black-200: hsl(210, 8%, 75%);
    --black-300: hsl(210, 8%, 65%);
    --black-350: hsl(210, 8%, 60%);
    --black-400: hsl(210, 8%, 55%);
    --black-500: hsl(210, 8%, 45%);
    --black-600: hsl(210, 8%, 35%);
    --black-700: hsl(210, 8%, 25%);
    --black-750: hsl(210, 8%, 20%);
    --black-800: hsl(210, 8%, 15%);
    --black-900: hsl(210, 8%, 5%);
  }
`;

export default variables;
