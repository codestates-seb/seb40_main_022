import styled from 'styled-components';
import UserProfile from '../../images/userprofile.png';

export const LankBody = styled.section`
  margin-top: 10px;
`;
export const Lankcontents = styled.section`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #c1c1c1;
  width: 100%;
  height: 100px;
  .cont-picturebtn {
    background: none;
    border: none;
  }

  .cont {
    width: 150px;
  }
  > h1 {
    line-height: 70px;
    font-weight: bolder;
    font-size: 24px;
    min-width: 60px;
  }
  .cont-picture {
    max-width: 80px;
    height: 80px;
    border-radius: 40px;
    background-image: url(${UserProfile});
    background-size: cover;
    background-repeat: no-repeat;
    background-position: 80% 100%;
    margin-right: 20px;
    cursor: pointer;
  }
  .challenge {
    width: 100px;
    height: 55px;
    background-color: var(--logored);
    border-radius: 11px;
    color: white;
    border: none;
    font-weight: bolder;
    margin-top: 7px;
    font-size: var(--font-18);
    box-shadow: var(--box-shadow);
    :hover {
      cursor: pointer;
      background-color: #fa8a8a;
    }
  }
  .challenging {
    width: 100px;
    height: 55px;
    background-color: var(--buttongray);
    border-radius: 11px;
    color: white;
    border: none;
    font-weight: bolder;
    margin-top: 7px;
    cursor: text;
    font-size: var(--font-18);
    box-shadow: var(--box-shadow);
  }
  > h4 {
    line-height: 70px;
    font-size: var(--font-11);
    font-weight: 600;
  }
  > :nth-child(7) {
    margin-left: 90px;
    margin-right: 90px;
  }
`;
