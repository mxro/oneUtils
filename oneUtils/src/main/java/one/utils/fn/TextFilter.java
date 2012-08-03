package one.utils.fn;

public interface TextFilter {

	public static TextFilter IDENTITY = new TextFilter() {

		@Override
		public String apply(final String text) {
			return text;
		}

	};

	public String apply(String text);

}
