/*
 * Copyright (c) 2014 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics.snowplow.storage.kinesis.s3

// AWS libs
import com.amazonaws.services.kinesis.model.Record

// AWS Kinesis Connector libs
import com.amazonaws.services.kinesis.connectors.interfaces.ITransformer

// Thrift libs
import org.apache.thrift.{TSerializer,TDeserializer}

// Snowplow thrift
import com.snowplowanalytics.snowplow.collectors.thrift.SnowplowRawEvent

/**
 * Thrift serializer/deserializer class
 */
class SnowplowRawEventTransformer extends ITransformer[ SnowplowRawEvent, SnowplowRawEvent ] {
  lazy val serializer = new TSerializer()
  lazy val deserializer = new TDeserializer()

  override def toClass(record: Record): SnowplowRawEvent = {
    var obj = new SnowplowRawEvent()
    deserializer.deserialize(obj, record.getData().array())
    obj
  }

  override def fromClass(record: SnowplowRawEvent) = record
}
