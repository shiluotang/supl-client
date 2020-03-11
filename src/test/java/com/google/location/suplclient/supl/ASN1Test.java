package com.google.location.suplclient.supl;

import java.net.UnknownHostException;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.location.suplclient.asn1.supl2.ulp.ULP_PDU;

public class ASN1Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(ASN1Test.class);

	@Test
	public void testEncoding() throws UnknownHostException, Exception {
		SuplMessagesGenerator messagesGenerator = new SuplLppMessagesGenerator();
		ULP_PDU pdu = messagesGenerator.newSuplStartMessage(null);
		byte[] uper = null;

		final int N = 0xfffff;
		long t1 = System.nanoTime();
		for (int i = 0, n = N; i < n; ++i)
			uper = messagesGenerator.encodeUlp(pdu);
		long t2 = System.nanoTime();
		LOGGER.info("UPER {}", DatatypeConverter.printHexBinary(uper));
		LOGGER.info("N = {}, Total = {} ms, Average = {} ms, TPS = {}",
				N,
				(t2 - t1) * 1e-6,
				(t2 - t1) * 1e-6 / N,
				N / ((t2 - t1) * 1e-9)
				);
	}
}
