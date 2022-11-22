import styled from 'styled-components';

const SideDiv = styled.div`
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-left: -80px;
  margin-top: 18px;
  background-color: var(--backwhite);
  @media screen and (max-width: 1400px) {
    margin-left: -100px;
  }
  .sidemenu {
    cursor: pointer;
    width: 150px;
    padding-top: 10%;
    padding-bottom: 10%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: var(--font-20);
    text-decoration: none;
    color: var(--footerblack);
    :hover {
      background-color: gray;
      color: #fff;
    }
  }
  box-shadow: var(--box-shadow);
`;

export default SideDiv;
