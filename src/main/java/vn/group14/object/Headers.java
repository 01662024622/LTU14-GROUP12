package vn.group14.object;
import vn.group14.enums.CmdCode;
public class Headers {
		private int lengthOfMessage=0 ;
		private short version =0;
		private short cmdCode;
		public Headers(short cmdCode, short version) {
			this.cmdCode = (short)(cmdCode%CmdCode.values().length);
			this.version=version;
		}
		public Headers(short cmdCode) {
			this.cmdCode = (short)(cmdCode%CmdCode.values().length);
		}
		public Headers() {}
		public int getLengthOfMessage() {
			return lengthOfMessage;
		}
		public void setLengthOfMessage(int lengthOfMessage) {
			this.lengthOfMessage = lengthOfMessage;
		}
		public short getVersion() {
			return version;
		}
		public void setVersion(short version) {
			this.version=version;
		}
		public short getCmdCode() {
			return cmdCode;
		}
		public void setCmdCode(short cmdCode) {
			this.cmdCode = (short)(cmdCode%CmdCode.values().length);
		}
	@Override
	public String toString() {
	// TODO Auto-generated method stub
	return CmdCode.values()[this.cmdCode]+"";
	}

}
