import styled from 'styled-components';

export const Dropdown = styled.main`
  display: flex;
`;
export const Dropdivi = styled.div`
  .container {
    max-width: 100px;
    width: 100%;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    .dropdown {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel {
      width: 100%;
      display: flex;
      cursor: pointer;
      > div {
        font-weight: 500;
        width: 100px;
        padding: 15px;
        font-size: var(--font-16);
        position: relative;
        text-align: center;
      }
      .caretIcon {
        position: absolute;
        top: 15px;
        right: 5px;
      }
    }
    .content {
      display: none;
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
      > button {
        width: 100%;
        padding: 5px;
        border: none;
        background-color: #fff;
        cursor: pointer;
        :hover {
          background-color: black;
          color: #fff;
        }
      }
    }
    .dropdown:checked + label + div {
      display: flex;
      flex-direction: column;
      border-top: 1px solid #00000026;
    }
    .caretIcon {
      transition: transform 250ms ease-out;
    }
    .dropdown:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      > :nth-child(4) {
        border: none;
        padding: 0;
      }
    }
    .content ul li {
      margin: 1rem 0;
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
// export const DropHeight = styled.div`
//   .container2 {
//     min-width: 139px;
//     box-shadow: 0 4px 5px 0 #00000026;
//     position: relative;
//     margin-right: 10px;
//     #dropdown2 {
//       left: 0;
//       visibility: hidden;
//       position: absolute;
//     }
//     .dropdownLabel2 {
//       display: flex;
//       padding: 15px;
//       cursor: pointer;
//       > div {
//         padding-left: 20px;
//         font-weight: 500;
//         font-size: var(--font-16);
//       }
//     }
//     .content2 {
//       display: none;
//       position: absolute;
//       width: 100%;
//       left: 0;
//       background: white;
//       box-shadow: 0 4px 5px 0 #00000026;
//     }
//     #dropdown2:checked + label + div {
//       display: block;
//       border-top: 1px solid #00000026;
//     }
//     .caretIcon2 {
//       transition: transform 250ms ease-out;
//     }
//     #dropdown2:checked + label > .caretIcon2 {
//       transform: rotate(-180deg);
//     }
//     .content2 ul {
//       list-style-type: none;
//       padding: 12px;
//       margin: 0;
//       > :nth-child(4) {
//         border: none;
//         padding: 0;
//       }
//     }
//     .content2 ul li {
//       margin: 1rem 0;
//       cursor: pointer;
//       border-bottom: 1px solid #b2b2b2;
//       padding-bottom: 25px;
//     }
//   }
// `;
// export const DropWeight = styled.div`
//   .container3 {
//     min-width: 126px;
//     box-shadow: 0 4px 5px 0 #00000026;
//     position: relative;
//     margin-right: 10px;
//     #dropdown3 {
//       left: 0;
//       visibility: hidden;
//       position: absolute;
//     }
//     .dropdownLabel3 {
//       display: flex;
//       padding: 15px;
//       cursor: pointer;
//       > div {
//         font-weight: 500;
//         font-size: var(--font-16);
//         padding-left: 13px;
//       }
//     }
//     .content3 {
//       display: none;
//       position: absolute;
//       width: 100%;
//       left: 0;
//       background: white;
//       box-shadow: 0 4px 5px 0 #00000026;
//     }
//     #dropdown3:checked + label + div {
//       display: block;
//       border-top: 1px solid #00000026;
//     }
//     .caretIcon3 {
//       transition: transform 250ms ease-out;
//     }
//     #dropdown3:checked + label > .caretIcon3 {
//       transform: rotate(-180deg);
//     }
//     .content3 ul {
//       list-style-type: none;
//       padding: 12px;
//       margin: 0;
//       > :nth-child(6) {
//         border: none;
//         padding: 0;
//       }
//     }
//     .content3 ul li {
//       margin: 1rem 0;
//       cursor: pointer;
//       border-bottom: 1px solid #b2b2b2;
//       padding-bottom: 25px;
//     }
//   }
// `;
// export const DropCareer = styled.div`
//   .container4 {
//     min-width: 120px;
//     box-shadow: 0 4px 5px 0 #00000026;
//     position: relative;
//     margin-right: 10px;
//     #dropdown4 {
//       left: 0;
//       visibility: hidden;
//       position: absolute;
//     }
//     .dropdownLabel4 {
//       display: flex;
//       padding: 15px;
//       cursor: pointer;
//       > div {
//         font-weight: 500;
//         font-size: var(--font-16);
//         padding-left: 10px;
//       }
//     }
//     .content4 {
//       display: none;
//       position: absolute;
//       width: 100%;
//       left: 0;
//       background: white;
//       box-shadow: 0 4px 5px 0 #00000026;
//     }
//     #dropdown4:checked + label + div {
//       display: block;
//       border-top: 1px solid #00000026;
//     }
//     .caretIcon4 {
//       transition: transform 250ms ease-out;
//     }
//     #dropdown4:checked + label > .caretIcon4 {
//       transform: rotate(-180deg);
//     }
//     .content4 ul {
//       list-style-type: none;
//       padding: 12px;
//       margin: 0;
//       > :nth-child(5) {
//         border: none;
//         padding: 0;
//       }
//     }
//     .content4 ul li {
//       margin: 1rem 0;
//       cursor: pointer;
//       border-bottom: 1px solid #b2b2b2;
//       padding-bottom: 25px;
//     }
//   }
// `;
// export const DropPoint = styled.div`
//   .container5 {
//     min-width: 141px;
//     box-shadow: 0 4px 5px 0 #00000026;
//     position: relative;
//     margin-right: 10px;
//     #dropdown5 {
//       left: 0;
//       visibility: hidden;
//       position: absolute;
//     }
//     .dropdownLabel5 {
//       display: flex;
//       padding: 15px;
//       cursor: pointer;
//       > div {
//         font-weight: 500;
//         font-size: var(--font-16);
//         padding-left: 24px;
//       }
//     }
//     .content5 {
//       display: none;
//       position: absolute;
//       width: 100%;
//       left: 0;
//       background: white;
//       box-shadow: 0 4px 5px 0 #00000026;
//     }
//     #dropdown5:checked + label + div {
//       display: block;
//       border-top: 1px solid #00000026;
//     }
//     .caretIcon5 {
//       transition: transform 250ms ease-out;
//     }
//     #dropdown5:checked + label > .caretIcon5 {
//       transform: rotate(-180deg);
//     }
//     .content5 ul {
//       list-style-type: none;
//       padding: 12px;
//       margin: 0;
//       > :nth-child(4) {
//         border: none;
//         padding: 0;
//       }
//     }
//     .content5 ul li {
//       margin: 1rem 0;
//       cursor: pointer;
//       border-bottom: 1px solid #b2b2b2;
//       padding-bottom: 25px;
//     }
//   }
// `;
