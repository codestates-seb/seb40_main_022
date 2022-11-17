import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { AddComment, CommentInput } from './MainStyle';

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
