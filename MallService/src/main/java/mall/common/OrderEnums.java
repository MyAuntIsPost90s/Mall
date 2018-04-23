package mall.common;

public class OrderEnums {

	public enum OrderFormType {
		DAY("d", "天"), MONTH("m", "月"), YEAR("y", "年");
		public String value;
		public String valueZh;

		OrderFormType(String value, String valueZh) {
			this.value = value;
			this.valueZh = valueZh;
		}
	}

	public enum OrderType {
		LOG(1, "记录单"), USER(2, "用户单");
		public int value;
		public String valueZh;

		OrderType(int value, String valueZh) {
			this.value = value;
			this.valueZh = valueZh;
		}
	}

	public enum OrderStatus {
		UNSEND(0, "未发货"), SEND(1, "已发货"), BACK(2, "退单");
		public int value;
		public String valueZh;

		OrderStatus(int value, String valueZh) {
			this.value = value;
			this.valueZh = valueZh;
		}
	}
}
