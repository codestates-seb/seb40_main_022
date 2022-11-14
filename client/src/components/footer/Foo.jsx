import styled from 'styled-components';

const Foo = styled.footer`
  width: 100%;
  background-color: var(--footerblack);
  > div {
    max-width: 1200px;
    padding-top: 155px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    @media screen and (max-width: 700px) {
      padding-top: 0px;
      padding: 30px;
      height: 210px;
      flex-direction: column;
    }
    .footer-left {
      .footer-images {
        > img {
          margin-right: 15px;
          margin-bottom: 10px;
        }
      }
      .footer-callenge {
        font-style: italic;
        font-weight: 700;
        color: var(--footerfont);
      }
    }
    .footer-right {
      display: flex;
      flex-direction: column;
      .right-one {
        margin-bottom: 5px;
      }
      > span {
        font-weight: 700;
        color: var(--footerfont);
      }
    }
  }
`;

export default Foo;
