import styled from 'styled-components';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';

export const AddComment = styled.div`
  display: flex;
  align-items: center;
  padding: 10px;
  .user {
    width: 45px;
    height: 45px;
    border-radius: 50%;
    object-fit: cover;
    margin-right: 10px;
  }

  > button {
    cursor: pointer;
    font-weight: bold;
    margin-left: 20px;
    color: var(--white);
    border: none;
    background-color: var(--white);
    border-radius: 50%;
    width: 40px;
    height: 40px;
    box-shadow: var(--box-shadow);

    .add {
      width: 40px;
      height: 40px;
      &:hover {
        filter: opacity(0.4) drop-shadow(0 0 0 #fc6666);
      }
    }
  }
`;

export const CommentInput = styled.input`
  width: 80%;
  height: 40px;
  border: none;
  box-shadow: inset var(--box-shadow);
  border-radius: 50px;
  outline: 1px solid var(--black-100);
  padding-left: 20px;

  &::placeholder {
    color: var(--black-300);
  }
`;

export default function DailyCmtInput() {
  return (
    <AddComment>
      <span className="cmtUserImg">
        <img className="user" src={daily} alt="daily" />
      </span>
      <CommentInput />
      <button>
        <img className="add" src={dailyAdd} alt="dailyAdd" />
      </button>
    </AddComment>
  );
}
